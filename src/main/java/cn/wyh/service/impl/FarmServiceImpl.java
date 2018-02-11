package cn.wyh.service.impl;

import cn.wyh.dao.FarmDao;
import cn.wyh.entity.Farm;
import cn.wyh.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
