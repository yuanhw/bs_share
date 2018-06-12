package cn.wyh.service.impl;

import cn.wyh.dao.*;
import cn.wyh.dto.CaiOneDto;
import cn.wyh.dto.CaiSearchDto;
import cn.wyh.entity.Address;
import cn.wyh.entity.CaiOrder;
import cn.wyh.entity.Info;
import cn.wyh.service.CaiService;
import cn.wyh.utils.InfoPushUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by WYH on 2018/4/20.
 */
@Service
public class CaiServiceImpl implements CaiService {
    @Autowired
    private CaiOrderMapper caiOrderMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private BlockPlantDao blockPlantDao;
    @Autowired
    private BlockDetailDao blockDetailDao;
    @Autowired
    private FarmDao farmDao;
    @Autowired
    private InfoMapper infoMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;


    @Override
    public int createCaiOrder(int plantId, int userId) {
        CaiOrder caiOrder = new CaiOrder();
        caiOrder.setStatus(0);
        caiOrder.setCreateTime(new Date());
        caiOrder.setUserId(userId);
        caiOrder.setPlantId(plantId);
        int fId = blockPlantDao.getFarmManagerIdByPlantId(plantId);
        caiOrder.setFarmId(fId);
        String address = addressMapper.getDefaultAddressByUserId(userId).getAddress();
        caiOrder.setAddress(address);
        caiOrderMapper.insertSelective(caiOrder);
        int blockId = blockPlantDao.selectByPrimaryKey(plantId).getBlockId();
        int fmId = blockDetailDao.selectFarmManagerIdByBlockId(blockId);
        String farmName = farmDao.selectFarmByFmManagerId(fmId).getFmTitle();
        Info info = new Info();
        info.setCreateTime(new Date());
        info.setTitle(farmName);
        info.setUserId(userId);
        info.setFmId(fmId);
        info.setContext("采摘指令下达成功~");
        infoMapper.insertSelective(info);
        String token = userTokenMapper.selectUserTokenByUserId(userId).getDeviceToken();
        InfoPushUtil.Message message = new InfoPushUtil.Message("共享农田", "采摘指令下达成功~", token);
        InfoPushUtil.sendMessage(message);
        return 1;
    }

    @Override
    public int isHasCaiOrder(int plantId) {
        return caiOrderMapper.getNumByPlantId(plantId);
    }

    @Override
    public List<CaiOneDto> loadCaiList(int userId, int status) {
        return caiOrderMapper.selectCaiOrder(userId, status);
    }

    @Override
    public int isHasDefaultAddress(int userId) {
        Address address = addressMapper.getDefaultAddressByUserId(userId);
        if (address != null) {
            return 1;
        }
        return 0;
    }

    @Override
    public JSONObject loadCaiListForWeb(CaiSearchDto search) {
        search.setStartIndex((search.getCurrentPage() - 1) * 5);
        JSONObject obj = new JSONObject();
        int num = caiOrderMapper.getCaiOrderTotalForWeb(search);
        obj.put("total", num);
        obj.put("list", caiOrderMapper.selectCaiOrderWeb(search));
        return obj;
    }

    @Override
    public int processCai(int caiId, String operator, String phone) {
        CaiOrder order = new CaiOrder();
        order.setId(caiId);
        order.setOperator(operator);
        order.setPhone(phone);
        order.setResTime(new Date());
        order.setStatus(1);
        caiOrderMapper.updateByPrimaryKeySelective(order);
        CaiOrder order1 = caiOrderMapper.selectByPrimaryKey(caiId);
        Info info = new Info();
        info.setCreateTime(new Date());
        String farmName = farmDao.selectFarmByFmManagerId(order1.getFarmId()).getFmTitle();
        info.setTitle(farmName);
        info.setUserId(order1.getUserId());
        info.setFmId(order1.getFarmId());
        info.setContext("采摘指令已受理~");
        infoMapper.insertSelective(info);
        String token = userTokenMapper.selectUserTokenByUserId(order1.getUserId()).getDeviceToken();
        InfoPushUtil.Message message = new InfoPushUtil.Message("共享农田", "采摘指令已受理~", token);
        InfoPushUtil.sendMessage(message);
        return 1;
    }

    @Override
    public int updateStatus(int id, int status) {
        CaiOrder order = new CaiOrder();
        order.setId(id);
        order.setStatus(status);
        String str = "";
        switch (status) {
            case 2:
                order.setSendTime(new Date());
                str = "采摘编号：" + id + " 已发货";
                break;
            case 3:
                order.setFinishTime(new Date());
                str = "采摘编号：" + id + " 已确认收货";
                break;
        }
        caiOrderMapper.updateByPrimaryKeySelective(order);
        CaiOrder order1 = caiOrderMapper.selectByPrimaryKey(id);
        Info info = new Info();
        info.setCreateTime(new Date());
        String farmName = farmDao.selectFarmByFmManagerId(order1.getFarmId()).getFmTitle();
        info.setTitle(farmName);
        info.setUserId(order1.getUserId());
        info.setFmId(order1.getFarmId());
        info.setContext(str);
        infoMapper.insertSelective(info);
        String token = userTokenMapper.selectUserTokenByUserId(order1.getUserId()).getDeviceToken();
        InfoPushUtil.Message message = new InfoPushUtil.Message("共享农田", str, token);
        InfoPushUtil.sendMessage(message);
        return 1;
    }
}
