package cn.wyh.service;

import cn.wyh.dto.BlockDetailResponseDto;
import cn.wyh.dto.BlockDetailSearch;
import cn.wyh.dto.SelfBDetail;

import java.util.List;

/**
 * Created by WYH on 2018/3/12.
 */
public interface BlockService {
    BlockDetailResponseDto loadBlockDetail(BlockDetailSearch blockDetailSearch);

    List<SelfBDetail> loadDetailForApp(int userId, int status);
}
