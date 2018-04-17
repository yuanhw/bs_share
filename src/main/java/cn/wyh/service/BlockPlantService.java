package cn.wyh.service;

import cn.wyh.dto.BlockPlantDto;
import cn.wyh.dto.BlockPlantSearch;
import cn.wyh.dto.CurrentStatus;
import cn.wyh.dto.TillageDto;
import cn.wyh.entity.Green;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by WYH on 2018/4/15.
 */
public interface BlockPlantService {
    List<Green> loadGreenList();

    int addPlantOrder(int blockId, int greenId);

    CurrentStatus currentPlantStatus(int blockId);

    List<BlockPlantDto> loadBlockPlantStatus(int userId, int status);

    JSONObject loadPlantListForWeb(BlockPlantSearch search);

    int processPlant(BlockPlantDto dto);

    JSONObject loadStatusForWeb(BlockPlantSearch search);

    int addTillageInfo(TillageDto dto);
}
