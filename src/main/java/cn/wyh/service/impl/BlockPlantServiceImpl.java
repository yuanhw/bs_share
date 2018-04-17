package cn.wyh.service.impl;

import cn.wyh.dao.BlockDetailDao;
import cn.wyh.dao.BlockPlantDao;
import cn.wyh.dao.GreenDao;
import cn.wyh.dto.BlockPlantDto;
import cn.wyh.dto.CurrentStatus;
import cn.wyh.entity.BlockPlant;
import cn.wyh.entity.Green;
import cn.wyh.service.BlockPlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by WYH on 2018/4/15.
 */
@Service
public class BlockPlantServiceImpl implements BlockPlantService {
    @Autowired
    private GreenDao greenDao;
    @Autowired
    private BlockPlantDao blockPlantDao;
    @Autowired
    private BlockDetailDao blockDetailDao;

    @Override
    public List<Green> loadGreenList() {
        return greenDao.selectList();
    }

    @Transactional
    @Override
    public int addPlantOrder(int blockId, int greenId) {
        try {
            BlockPlant plant = new BlockPlant();
            plant.setBlockId(blockId);
            plant.setGreenId(greenId);
            plant.setCreateTime(new Date());
            plant.setStatus(1);
            blockPlantDao.insertBackId(plant);
            blockDetailDao.updateTillCodeById(blockId, plant.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public CurrentStatus currentPlantStatus(int blockId) {
        int tillageId = blockDetailDao.getDetailById(blockId).getTillId();
        CurrentStatus current = blockPlantDao.selectCurrentStatusById(tillageId);
        if (current.getTillageId() == null) {
            current.setStatus("暂无");
            current.setOperate("暂无");
        } else {

        }
        return current;
    }

    @Override
    public List<BlockPlantDto> loadBlockPlantStatus(int userId, int status) {
        return blockPlantDao.selectPlantStatus(userId, status);
    }
}
