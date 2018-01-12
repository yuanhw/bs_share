package cn.wyh.service.impl;

import cn.wyh.dao.FarmManagerDao;
import cn.wyh.entity.FarmManager;
import cn.wyh.service.FarmManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WYH on 2017/12/18.
 */
@Service
public class FarmManagerServiceImpl implements FarmManagerService {

    @Autowired
    private FarmManagerDao farmManagerDao;

    @Override
    public FarmManager loadObjByPhone(String phone) {
        return this.farmManagerDao.findByPhone(phone);
    }

    @Override
    public void reg(FarmManager farmManager) {
        this.farmManagerDao.addObj(farmManager);
    }

    @Override
    public void updateStatus(int status, String phone) {
        this.farmManagerDao.updateStatus(status, phone);
    }

    @Override
    public List<FarmManager> loadData(String phone, String fmName, int status, int start) {
        return this.farmManagerDao.findList(phone, fmName, status, start);
    }

    @Override
    public int totalNums(String phone, String fmName, int status) {
        return this.farmManagerDao.totalRowNumByConditions(phone, fmName, status);
    }

    @Override
    public void deleteByPhone(String phone) {
        this.farmManagerDao.deleteByPhone(phone);
    }
}
