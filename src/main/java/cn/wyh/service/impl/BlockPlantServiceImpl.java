package cn.wyh.service.impl;

import cn.wyh.dao.*;
import cn.wyh.dto.*;
import cn.wyh.entity.*;
import cn.wyh.service.BlockPlantService;
import cn.wyh.utils.InfoPushUtil;
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
    @Autowired
    private FarmDao farmDao;
    @Autowired
    private InfoMapper infoMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

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
            //
            int userId = blockDetailDao.getDetailById(blockId).getUserId();
            int fmId = blockDetailDao.selectFarmManagerIdByBlockId(blockId);
            String farmName = farmDao.selectFarmByFmManagerId(fmId).getFmTitle();
            Info info = new Info();
            info.setCreateTime(new Date());
            info.setTitle(farmName);
            info.setUserId(userId);
            info.setFmId(fmId);
            info.setContext("种植指令发送成功~");
            infoMapper.insertSelective(info);
            String token = userTokenMapper.selectUserTokenByUserId(userId).getDeviceToken();
            InfoPushUtil.Message message = new InfoPushUtil.Message("共享农田", "种植指令下达成功，12小时内将被执行~", token);
            InfoPushUtil.sendMessage(message);
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
            Tillage obj = tillageMapper.selectByPrimaryKey(current.getTillageId());
            if (obj != null) {
                current.setStatus(obj.getGrowStatus());
                current.setOperate(obj.getLastOperation());
                if (current.getDay() != null && current.getDay() < 0) {
                    current.setDay(0);
                }
            }
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
        int blockId = blockPlantDao.selectByPrimaryKey(dto.getPlantId()).getBlockId();
        int userId = blockDetailDao.getDetailById(blockId).getUserId();
        int fmId = blockDetailDao.selectFarmManagerIdByBlockId(blockId);
        String farmName = farmDao.selectFarmByFmManagerId(fmId).getFmTitle();
        Info info = new Info();
        info.setCreateTime(new Date());
        info.setTitle(farmName);
        info.setUserId(userId);
        info.setFmId(fmId);
        info.setContext("种植指令已受理~");
        infoMapper.insertSelective(info);
        String token = userTokenMapper.selectUserTokenByUserId(userId).getDeviceToken();
        InfoPushUtil.Message message = new InfoPushUtil.Message("共享农田", "种植指令已受理~", token);
        InfoPushUtil.sendMessage(message);
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
            tillageImgListMapper.insertSelective(img);
        }
        int i = tillageMapper.getTotalByPlantId(dto.getPlantId());
        BlockPlant obj = new BlockPlant();
        obj.setId(dto.getPlantId());
        obj.setTillageId(id);
        if (i == 1) {
            obj.setStartTime(new Date());
        }
        blockPlantDao.updateByPrimaryKeySelective(obj);
        //
        int blockId = blockPlantDao.selectByPrimaryKey(dto.getPlantId()).getBlockId();
        int userId = blockDetailDao.getDetailById(blockId).getUserId();
        int fmId = blockDetailDao.selectFarmManagerIdByBlockId(blockId);
        String farmName = farmDao.selectFarmByFmManagerId(fmId).getFmTitle();
        Info info = new Info();
        info.setCreateTime(new Date());
        info.setTitle(farmName);
        info.setUserId(userId);
        info.setFmId(fmId);
        info.setContext("地块状态更新~");
        infoMapper.insertSelective(info);
        String token = userTokenMapper.selectUserTokenByUserId(userId).getDeviceToken();
        InfoPushUtil.Message message = new InfoPushUtil.Message("共享农田", "地块状态更新~", token);
        InfoPushUtil.sendMessage(message);
        return 1;
    }

    @Override
    public List<TillageDto> loadTillageList(int plantId) {
        List<TillageDto> list = tillageMapper.selectListByPlantId(plantId);
        for (TillageDto dto : list) {
            int id = dto.getTillageId();
            dto.setImgList(tillageImgListMapper.selectImgListByTillageId(id));
        }
        return list;
    }

    @Override
    public List<String> loadTillageImgList(int plantId) {
        BlockPlant obj = blockPlantDao.selectByPrimaryKey(plantId);
        int id = obj.getTillageId();
        return tillageImgListMapper.selectImgListByTillageId(id);
    }

}
