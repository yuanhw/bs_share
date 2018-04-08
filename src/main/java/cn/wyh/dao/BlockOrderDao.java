package cn.wyh.dao;

import cn.wyh.dto.TabAllOrder;
import cn.wyh.entity.BlockOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by WYH on 2018/4/6.
 */
public interface BlockOrderDao {
    int createOrder(BlockOrder order);

    List<TabAllOrder> loadOrderTabList(@Param("userId") String userId);
}
