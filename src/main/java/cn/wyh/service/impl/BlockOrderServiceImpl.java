package cn.wyh.service.impl;

import cn.wyh.dao.*;
import cn.wyh.dto.*;
import cn.wyh.entity.*;
import cn.wyh.service.BlockOrderService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by WYH on 2018/4/6.
 */
@Service
public class BlockOrderServiceImpl implements BlockOrderService {
    @Autowired
    private BlockOrderDao blockOrderDao;
    @Autowired
    private FarmDao farmDao;
    @Autowired
    private BlockRuleDao blockRuleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BlockDetailDao blockDetailDao;
    @Autowired
    private FarmManagerDao farmManagerDao;
    @Autowired
    private BlockOrderItemDao blockOrderItemDao;
    @Autowired
    private RefundDao refundDao;

    @Override
    public ShareOrderDto getShareOrderByBatchNo(String batchNo) {
        ShareOrderDto obj = this.farmDao.selectFarmByBatch(batchNo);
        BlockRule obj2 = this.blockRuleDao.selectBlockRuleByBatch(batchNo);
        int num = this.blockRuleDao.getNumCan(batchNo);
        obj.setBlockType(obj2.getType());
        obj.setMaxNum(num);
        obj.setNum(1);
        if (obj2.getType() == 0) {
            obj.setBlockTypeValue("仅自种");
        } else {
            obj.setBlockTypeValue("可代种");
        }
        int max = Integer.parseInt(obj2.getMaxLease());
        obj.setMaxLease(max);
        obj.setLeaseTime(1);
        obj.setUnitLease(obj2.getLeaseUnit());
        obj.setUnitPrice(obj2.getUnitPrice());
        obj.setAmount(obj2.getUnitPrice());
        obj.setSpec(obj2.getSpec());
        obj.setOrderId(createOrderId());
        obj.setBatchNo(batchNo);
        return obj;
    }

    @Transactional
    @Override
    public int createBlockOrder(User user, ShareOrderDto obj) {
        try {
            double account = user.getAccount() - obj.getAmount();
            this.userDao.updateAccount(account, user.getUserPhone());
            FarmManager fm = this.blockRuleDao.selectFarmManagerByBatchNo(obj.getBatchNo());
            double account2 = fm.getAccount() + obj.getAmount();
            this.farmManagerDao.updateFMAccount(account2, fm.getPhone());
            this.farmDao.updateConsumerNum(fm.getFmId());
            Date now = new Date();
            Date end = computDate(now, obj.getLeaseTime(), obj.getUnitLease());
            BlockOrder order = new BlockOrder(obj.getOrderId(), now, now, end, obj.getNum(),
                    obj.getSpec(),
                    obj.getAmount() + "",
                    obj.getLeaseTime(),
                    user.getId(),
                    fm.getFmId(),
                    obj.getBatchNo(),
                    1,
                    obj.getBlockType());
            this.blockOrderDao.createOrder(order);
            UpdateUserOrder uo = new UpdateUserOrder(order.getUserId(), order.getType(), obj.getLeaseTime() + obj.getUnitLease(), now,
                    1, obj.getBatchNo(), 0);
            List<Integer> bId = this.blockDetailDao.getNullBlockDetail(obj.getBatchNo(), obj.getNum());
            if (bId.size() != obj.getNum()) {
                throw new Exception("地块数量不足");
            } else {
                for (int id : bId) {
                    uo.setId(id);
                    this.blockDetailDao.updateUserOrder(uo);
                    this.blockOrderItemDao.addOrderItem(new BlockOrderItem(obj.getOrderId(), id + "", new Date()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public List<TabAllOrder> loadTabOrderList1(String userId, int status) {
        List<TabAllOrder> data = this.blockOrderDao.loadOrderTabList(userId, status);
        for (TabAllOrder item : data) {
            item.setSpec("地块规格：" + item.getSpec());
            item.setNum("数量：" + item.getNum());
            item.setTime("时间：" + item.getTime());
            item.setCreateTime("生效时间：" + item.getCreateTime().split("[.]")[0]);
            if ((item.getEndTime().getTime() - new Date().getTime() < 0) && item.getStatus().equals("1")) {
                item.setStatus("待核销");
            } else {
                item.setStatus(convertStatus(item.getStatus()));
            }
            item.setPrice("￥" + item.getPrice());
        }
        return data;
    }

    @Override
    public JSONObject loadOrderWeb(BlockOrderWebDto param) {
        param.setStartIndex(5 * (param.getCurrentPage() - 1));
        JSONObject obj = new JSONObject();
        obj.put("total", this.blockOrderDao.getTotalWeb(param));
        obj.put("list", this.blockOrderDao.loadOrderWeb(param));
        return obj;
    }

    @Transactional
    @Override
    public int applyRefund(Refund refund) {
        try {
            BlockOrder order = this.blockOrderDao.selectOrderByOrderId(refund.getOrderId());
            refund.setUserId(order.getUserId());
            refund.setFarmManagerId(order.getFarmManagerId());
            refund.setStatus(0);
            refund.setCreateTime(new Date());
            this.blockOrderDao.undateStatus(order.getOrderId(), 3);
            this.refundDao.insertSelective(refund);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public List<RefundListDto> loadRefundList(int userId) {
        return this.refundDao.selectRefundListDtoByUserId(userId);
    }

    @Transactional
    @Override
    public int cancelRefund(String orderId) {
        try {
            this.refundDao.updateStatusByOrderId(1, orderId);
            this.blockOrderDao.undateStatus(orderId, 1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return 1;
    }

    @Transactional
    @Override
    public int heXiao(String orderId) {
        try {
            this.blockOrderDao.undateStatus(orderId, 2);
            List<Integer> list = this.blockDetailDao.getIdList(orderId);
            for (int id : list) {
                this.blockDetailDao.updateStatusById(2, id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public int delRefund(String orderId) {
        this.refundDao.updateStatusByOrderId(4, orderId);
        return 1;
    }

    @Override
    public int delOrder(String orderId) {
        this.blockOrderDao.undateStatus(orderId, 5);
        return 1;
    }

    @Transactional
    @Override
    public int orderComment(String orderId, int rating, String comment) {
        try {
            this.blockOrderDao.undateComment(orderId, rating, comment);
            this.blockOrderDao.undateStatus(orderId, 0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return 1;
    }

    private String convertStatus(String status) {
        switch (status.charAt(0)) {
            case '0':
                return "交易完成";
            case '1':
                return "正常";
            case '2':
                return "待评价";
            case '3':
                return "退款中";
            case '4':
                return "已退款";
            case '5':
                return "已删除";
            default:
                return "交易完成";
        }
    }
    private Date computDate(Date now, int leaseTime, String unitLease) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        if ("月".equals(unitLease)) {
            calendar.add(calendar.MONTH, leaseTime);
        } else if ("年".equals(unitLease)) {
            calendar.add(calendar.YEAR, leaseTime);
        }
        return calendar.getTime();
    }

    /**
     * 生成订单编号
     *
     * @return
     */
    private String createOrderId() {
        return "o" + (new Date().getTime());
    }
}
