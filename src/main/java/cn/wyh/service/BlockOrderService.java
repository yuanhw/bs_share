package cn.wyh.service;

import cn.wyh.dto.ShareOrderDto;
import cn.wyh.entity.User;

/**
 * Created by WYH on 2018/4/6.
 */
public interface BlockOrderService {
    ShareOrderDto getShareOrderByBatchNo(String batchNo);

    int createBlockOrder(User user, ShareOrderDto obj);
}
