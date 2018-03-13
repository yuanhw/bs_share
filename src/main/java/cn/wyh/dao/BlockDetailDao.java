package cn.wyh.dao;

import cn.wyh.dto.BlockDetailSearch;
import cn.wyh.entity.BlockDetail;

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
}
