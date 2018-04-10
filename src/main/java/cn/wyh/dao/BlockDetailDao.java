package cn.wyh.dao;

import cn.wyh.dto.BlockDetailSearch;
import cn.wyh.dto.UpdateUserOrder;
import cn.wyh.entity.BlockDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by WYH on 2018/3/9.
 */
public interface BlockDetailDao {
    void createBlockDetail(BlockDetail blockDetail);

    int canDel(String branchNo);

    int deleteBlockDetail(String branchNo);

    List<BlockDetail> selectDetailList(BlockDetailSearch search);

    int getDetailTotal(BlockDetailSearch search);

    int updateUserOrder(UpdateUserOrder blockDetail);

    List<Integer> getNullBlockDetail(@Param("branchNo") String branchNo, @Param("limit") int limit);

    int updateStatusById(@Param("status") int status, @Param("id") int id);

    List<Integer> getIdList(@Param("orderId") String orderId);
}
