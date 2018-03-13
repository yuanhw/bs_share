package cn.wyh.service.impl;

import cn.wyh.dao.BlockDetailDao;
import cn.wyh.dto.BlockDetailResponseDto;
import cn.wyh.dto.BlockDetailSearch;
import cn.wyh.entity.BlockDetail;
import cn.wyh.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WYH on 2018/3/12.
 */
@Service
public class BlockServiceImpl implements BlockService {
    @Autowired
    private BlockDetailDao blockDetailDao;

    @Override
    public BlockDetailResponseDto loadBlockDetail(BlockDetailSearch blockDetailSearch) {
        blockDetailSearch.setStartIndex((blockDetailSearch.getCurrentPage() - 1) * 5);
        int total = this.blockDetailDao.getDetailTotal(blockDetailSearch);
        List<BlockDetail> list = this.blockDetailDao.selectDetailList(blockDetailSearch);
        BlockDetailResponseDto dto = new BlockDetailResponseDto(list, total);
        return dto;
    }
}
