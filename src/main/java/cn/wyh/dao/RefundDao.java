package cn.wyh.dao;

import cn.wyh.dto.RefundListDto;
import cn.wyh.entity.Refund;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RefundDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Refund record);

    int insertSelective(Refund record);

    Refund selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Refund record);

    int updateByPrimaryKey(Refund record);

    List<RefundListDto> selectRefundListDtoByUserId(@Param("userId") int userId);

    int updateStatusByOrderId(@Param("status") int status, @Param("orderId") String orderId);
}