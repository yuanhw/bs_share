package cn.wyh.dao;

import cn.wyh.entity.BlockOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by WYH on 2018/4/8.
 */
public interface BlockOrderItemDao {
    int addOrderItem(BlockOrderItem item);

    List<BlockOrderItem> selectList(@Param("orderId") String orderId);
}
