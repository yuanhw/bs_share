package cn.wyh.service.impl;

import cn.wyh.dao.FarmDao;
import cn.wyh.dto.LateLySimplyFarm;
import cn.wyh.entity.Farm;
import cn.wyh.service.FarmService;
import cn.wyh.utils.BaiDuDistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WYH on 2018/2/7.
 */
@Service
public class FarmServiceImpl implements FarmService {
    @Autowired
    private FarmDao farmDao;

    @Override
    public Farm loadFarmByFmId(String fmId) {
        return farmDao.selectFarmById(fmId);
    }

    @Override
    public Farm loadFarmById(String id) {
        return this.farmDao.selectFarmForAppById(id);
    }

    @Override
    public int isHaveFarmById(String fmId) {
        Farm farm = this.farmDao.selectFarmById(fmId);
        if (farm == null) {
            return 0;
        }
        return 1;
    }

    @Override
    public int addFarmBaseInfo(Farm farm) {
        return this.farmDao.addFarmBaseInfo(farm);
    }

    @Override
    public int updateFarmBaseInfo(Farm farm) {
        return this.farmDao.updateFarmInfo(farm);
    }

    @Override
    public int updateStatus(int code, Integer id) {
        return this.farmDao.updateStatus(code, id);
    }

    @Override
    public int updateImg(String imgFile, Integer id) {
        return this.farmDao.updateImg(imgFile, id);
    }

    @Override
    public List<LateLySimplyFarm> selectFarmByPos(Double lat, Double lng) {
        List<LateLySimplyFarm> resp = this.farmDao.selectFarmByPos(lat, lng);
        for (LateLySimplyFarm farm : resp) {
            farm.setDistance(BaiDuDistanceUtil.distanceFormat(Double.parseDouble(farm.getDistance())));
        }
        return resp;
    }

    @Override
    public List<Farm> selectFarmListByCond(String sid, Integer status) {
        return this.farmDao.selectFarmListByCond(sid, status);
    }

    @Override
    public List<LateLySimplyFarm> selectFarmByCity(String city) {
        return this.farmDao.selectSFarmListByCity(city);
    }

    @Override
    public List<LateLySimplyFarm> selectFarm2(String city, String query) {
        return this.farmDao.selectSFarmList2(city, query);
    }
}
