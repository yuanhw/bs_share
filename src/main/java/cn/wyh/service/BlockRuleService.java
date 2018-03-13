package cn.wyh.service;

import cn.wyh.dto.BlockSearch;
import cn.wyh.entity.BlockRule;

import java.util.List;

/**
 * Created by WYH on 2018/3/8.
 */
public interface BlockRuleService {
    List<BlockRule> loadBlockRuleList(BlockSearch blockSearch);

    int getBlockRuleListTotal(BlockSearch blockSearch);

    void createBranchBlock(BlockRule blockRule);

    int canDel(String branchNo);

    boolean deleteBlock(String branchNo);
}
