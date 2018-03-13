package cn.wyh.dao;

import cn.wyh.dto.BlockSearch;
import cn.wyh.entity.BlockRule;

import java.util.List;

/**
 * Created by WYH on 2018/3/8.
 */
public interface BlockRuleDao {
    List<BlockRule> selectBlockRuleList(BlockSearch blockSearch);

    int selectTotal(BlockSearch blockSearch);

    int createBlockRule(BlockRule blockRule);

    int deleteBlockRule(String branchNo);
}
