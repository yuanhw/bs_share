package cn.wyh.dao;

import cn.wyh.dto.BlockRuleShowList;
import cn.wyh.dto.BlockSearch;
import cn.wyh.entity.BlockRule;
import cn.wyh.entity.FarmManager;

import java.util.List;

/**
 * Created by WYH on 2018/3/8.
 */
public interface BlockRuleDao {
    List<BlockRule> selectBlockRuleList(BlockSearch blockSearch);

    int selectTotal(BlockSearch blockSearch);

    int createBlockRule(BlockRule blockRule);

    int deleteBlockRule(String branchNo);

    List<BlockRuleShowList> selectRuleListById(String id);

    List<String> getMaxLease(String id);

    List<String> getSpec(String id);

    int getNumCan(String batchNo);

    BlockRule selectBlockRuleByBatch(String batchNo);

    FarmManager selectFarmManagerByBatchNo(String batchNo);
}
