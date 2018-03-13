package cn.wyh.service;

import cn.wyh.dto.BlockDetailResponseDto;
import cn.wyh.dto.BlockDetailSearch;

/**
 * Created by WYH on 2018/3/12.
 */
public interface BlockService {
    BlockDetailResponseDto loadBlockDetail(BlockDetailSearch blockDetailSearch);
}
