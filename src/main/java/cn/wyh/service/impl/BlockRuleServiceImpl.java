package cn.wyh.service.impl;

import cn.wyh.dao.BlockDetailDao;
import cn.wyh.dao.BlockRuleDao;
import cn.wyh.dto.BlockSearch;
import cn.wyh.entity.BlockDetail;
import cn.wyh.entity.BlockRule;
import cn.wyh.service.BlockRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by WYH on 2018/3/8.
 */
@Service
public class BlockRuleServiceImpl implements BlockRuleService {

    @Autowired
    private BlockRuleDao blockRuleDao;

    @Autowired
    private BlockDetailDao blockDetailDao;

    @Override
    public List<BlockRule> loadBlockRuleList(BlockSearch blockSearch) {
        return this.blockRuleDao.selectBlockRuleList(blockSearch);
    }

    @Override
    public int getBlockRuleListTotal(BlockSearch blockSearch) {
        return this.blockRuleDao.selectTotal(blockSearch);
    }

    @Override
    public void createBranchBlock(BlockRule blockRule) {
        Date now = new Date();
        blockRule.setCreateTime(now);
        this.blockRuleDao.createBlockRule(blockRule);
        int count = blockRule.getNumber();
        for (int i = 0; i < count; i++) {
            BlockDetail blockDetail = new BlockDetail();
            blockDetail.setBranchNo(blockRule.getBatchNo());
            blockDetail.setType(blockRule.getType());
            blockDetail.setCrateTime(now);
            this.blockDetailDao.createBlockDetail(blockDetail);
        }
    }

    @Override
    public int canDel(String branchNo) {
        return this.blockDetailDao.canDel(branchNo);
    }

    @Override
    public boolean deleteBlock(String branchNo) {
        this.blockDetailDao.deleteBlockDetail(branchNo);
        this.blockRuleDao.deleteBlockRule(branchNo);
        return true;
    }
}
