package cn.wyh.service.impl;

import cn.wyh.dao.*;
import cn.wyh.dto.*;
import cn.wyh.entity.BlockPlant;
import cn.wyh.entity.Green;
import cn.wyh.entity.Tillage;
import cn.wyh.entity.TillageImgList;
import cn.wyh.service.BlockPlantService;
import com.alibaba.fastjson.JSONObject;
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
    @Autowired
    private TillageMapper tillageMapper;
    @Autowired
    private TillageImgListMapper tillageImgListMapper;

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

    @Override
    public JSONObject loadPlantListForWeb(BlockPlantSearch search) {
        search.setStartIndex((search.getCurrentPage() - 1) * 5);
        JSONObject rt = new JSONObject();
        rt.put("total", blockPlantDao.getPlantForWebTotal(search));
        rt.put("list", blockPlantDao.selectPlantForWeb(search));
        return rt;
    }

    @Override
    public int processPlant(BlockPlantDto dto) {
        blockPlantDao.updateProcess(dto);
        return 1;
    }

    @Override
    public JSONObject loadStatusForWeb(BlockPlantSearch search) {
        search.setStartIndex((search.getCurrentPage() - 1) * 5);
        JSONObject rt = new JSONObject();
        rt.put("total", blockPlantDao.getTotal2(search));
        rt.put("list", blockPlantDao.selectStatus(search));
        return rt;
    }

    @Transactional
    @Override
    public int addTillageInfo(TillageDto dto) {
        Tillage tillage = new Tillage();
        tillage.setCurrentVideo(dto.getVideo());
        tillage.setGrowStatus(dto.getStatus());
        tillage.setPlantId(dto.getPlantId());
        tillage.setLastOperation(dto.getOperate());
        tillage.setCreateTime(new Date());
        tillageMapper.insertSelective(tillage);
        int id = tillage.getId();
        for (String str : dto.getImgList()) {
            TillageImgList img = new TillageImgList();
            img.setFilePath(str);
            img.setTillageId(id);
            img.setCreateTime(new Date());
        }
        BlockPlant obj = new BlockPlant();
        obj.setId(dto.getPlantId());
        obj.setTillageId(id);
        blockPlantDao.updateByPrimaryKeySelective(obj);
        return 1;
    }

}
