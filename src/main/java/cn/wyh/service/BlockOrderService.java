package cn.wyh.service;

import cn.wyh.dto.BlockOrderWebDto;
import cn.wyh.dto.RefundListDto;
import cn.wyh.dto.ShareOrderDto;
import cn.wyh.dto.TabAllOrder;
import cn.wyh.entity.Refund;
import cn.wyh.entity.User;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by WYH on 2018/4/6.
 */
public interface BlockOrderService {
    ShareOrderDto getShareOrderByBatchNo(String batchNo);

    int createBlockOrder(User user, ShareOrderDto obj);

    List<TabAllOrder> loadTabOrderList1(String userId, int status);

    JSONObject loadOrderWeb(BlockOrderWebDto param);

    int applyRefund(Refund refund);

    List<RefundListDto> loadRefundList(int userId);

    int cancelRefund(String orderId);

    int heXiao(String orderId);

    int delRefund(String orderId);

    int delOrder(String orderId);

    int orderComment(String orderId, int rating, String comment);
}
