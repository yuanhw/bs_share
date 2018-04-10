package cn.wyh.dao;

import cn.wyh.dto.BlockOrderWebDto;
import cn.wyh.dto.OrderWebResponseDto;
import cn.wyh.dto.TabAllOrder;
import cn.wyh.entity.BlockOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by WYH on 2018/4/6.
 */
public interface BlockOrderDao {
    int createOrder(BlockOrder order);

    List<TabAllOrder> loadOrderTabList(@Param("userId") String userId, @Param("status") int status);

    List<OrderWebResponseDto> loadOrderWeb(BlockOrderWebDto dto);

    int getTotalWeb(BlockOrderWebDto dto);

    BlockOrder selectOrderByOrderId(@Param("orderId") String orderId);

    int undateStatus(@Param("orderId") String orderId, @Param("status") int status);
}
