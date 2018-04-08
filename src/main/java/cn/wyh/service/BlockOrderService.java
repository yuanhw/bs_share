package cn.wyh.service;

import cn.wyh.dto.ShareOrderDto;
import cn.wyh.dto.TabAllOrder;
import cn.wyh.entity.User;

import java.util.List;

/**
 * Created by WYH on 2018/4/6.
 */
public interface BlockOrderService {
    ShareOrderDto getShareOrderByBatchNo(String batchNo);

    int createBlockOrder(User user, ShareOrderDto obj);

    List<TabAllOrder> loadTabOrderList1(String userId);
}
