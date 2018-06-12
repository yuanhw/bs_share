package cn.wyh.service;

import cn.wyh.dto.BlockRuleShowList;
import cn.wyh.dto.BlockSearch;
import cn.wyh.entity.BlockRule;

import java.util.List;
import java.util.Map;

/**
 * Created by WYH on 2018/3/8.
 */
public interface BlockRuleService {
    List<BlockRule> loadBlockRuleList(BlockSearch blockSearch);

    int getBlockRuleListTotal(BlockSearch blockSearch);

    void createBranchBlock(BlockRule blockRule);

    int canDel(String branchNo);

    boolean deleteBlock(String branchNo);

    List<BlockRuleShowList> loadRuleList(String id);

    Map<String, List<String>> getSpinnerP(String id);

    int getNumCan(String batch);
}
