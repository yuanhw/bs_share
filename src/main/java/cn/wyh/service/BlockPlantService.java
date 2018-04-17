package cn.wyh.service;

import cn.wyh.dto.BlockPlantDto;
import cn.wyh.dto.CurrentStatus;
import cn.wyh.entity.Green;

import java.util.List;

/**
 * Created by WYH on 2018/4/15.
 */
public interface BlockPlantService {
    List<Green> loadGreenList();

    int addPlantOrder(int blockId, int greenId);

    CurrentStatus currentPlantStatus(int blockId);

    List<BlockPlantDto> loadBlockPlantStatus(int userId, int status);
}
