package cn.wyh.web;

import cn.wyh.common.response.ResponseSuccess;
import cn.wyh.dto.BlockDetailSearch;
import cn.wyh.dto.BlockListResponseDto;
import cn.wyh.dto.BlockRuleShowList;
import cn.wyh.dto.BlockSearch;
import cn.wyh.entity.BlockRule;
import cn.wyh.entity.Farm;
import cn.wyh.service.BlockRuleService;
import cn.wyh.service.BlockService;
import cn.wyh.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by WYH on 2018/3/8.
 */
@RestController
@RequestMapping("/block")
public class BlockController {
    @Autowired
    private FarmService farmService;

    @Autowired
    private BlockRuleService blockRuleService;

    @Autowired
    private BlockService blockService;

    @RequestMapping("/loadBlockRuleList")
    public String loadBlockRuleList(@ModelAttribute BlockSearch blockSearch) {
        Farm farm = farmService.loadFarmByFmId(blockSearch.getFmManagerId() + "");
        blockSearch.setFarmId(farm.getId());
        blockSearch.setStartIndex((blockSearch.getCurrentPage() - 1) * 5);
        List<BlockRule> list = this.blockRuleService.loadBlockRuleList(blockSearch);
        int total = this.blockRuleService.getBlockRuleListTotal(blockSearch);
        BlockListResponseDto data = new BlockListResponseDto(list, total);
        ResponseSuccess resp = new ResponseSuccess(data);
        return resp.toString();
    }

    @RequestMapping("/createBranchBlock")
    public String createBranchBlock(@ModelAttribute BlockRule blockRule) {
        Farm farm = farmService.loadFarmByFmId(blockRule.getFarmId() + "");
        blockRule.setFarmId(farm.getId());
        this.blockRuleService.createBranchBlock(blockRule);
        ResponseSuccess resp = new ResponseSuccess(null);
        return resp.toString();
    }

    @RequestMapping("/canDelBlackRule")
    public String canDelBlackRule(@RequestParam String batchNo) {
        int data = this.blockRuleService.canDel(batchNo);
        ResponseSuccess resp = new ResponseSuccess(data);
        return resp.toString();
    }

    @RequestMapping("/deleteBlack")
    public String delBlackRuleAndDetail(@RequestParam String batchNo) {
        this.blockRuleService.deleteBlock(batchNo);
        ResponseSuccess resp = new ResponseSuccess(null);
        return resp.toString();
    }

    @RequestMapping("/loadBlockDetailList")
    public String loadBlockDetailList(@ModelAttribute BlockDetailSearch blockDetailSearch) {
        ResponseSuccess resp = new ResponseSuccess(this.blockService.loadBlockDetail(blockDetailSearch));
        return resp.toString();
    }

    @RequestMapping("/loadRuleList")
    public String loadBlockRuleShowList(@RequestParam String id) {
        List<BlockRuleShowList> data = this.blockRuleService.loadRuleList(id);
        ResponseSuccess resp = new ResponseSuccess(data);
        return resp.toString();
    }

    @RequestMapping("/getSpinnerP")
    public String getSpinnerP(@RequestParam String id) {
        Map<String, List<String>> data = this.blockRuleService.getSpinnerP(id);
        ResponseSuccess resp = new ResponseSuccess(data);
        return resp.toString();
    }

    @RequestMapping("/getNumCan")
    public String getNumCan(@RequestParam String batchNo) {
        return (new ResponseSuccess(this.blockRuleService.getNumCan(batchNo))).toString();
    }
}
