package cn.wyh.service.impl;

import cn.wyh.dao.BlockDetailDao;
import cn.wyh.dto.BlockDetailResponseDto;
import cn.wyh.dto.BlockDetailSearch;
import cn.wyh.dto.SelfBDetail;
import cn.wyh.entity.BlockDetail;
import cn.wyh.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public List<SelfBDetail> loadDetailForApp(int userId, int status) {
        List<SelfBDetail> list = blockDetailDao.selectBdetailForApp(userId, status);
        Date now = new Date();
        for (SelfBDetail item : list) {
            if (item.getNoValid().getTime() - now.getTime() > 0) {
                if (status == 1) {
                    if (item.getType() == 0) {
                        item.setStatus("未种植");
                    } else {
                        item.setStatus("已种植");
                    }
                } else {
                    item.setStatus("正常");
                }
            } else {
                item.setStatus("已过期");
            }
        }
        return list;
    }
}
