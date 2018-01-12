package cn.wyh.service.impl;

import cn.wyh.dao.PlatformManagerDao;
import cn.wyh.entity.PlatformManager;
import cn.wyh.service.PlatformManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by WYH on 2017/12/13.
 */
@Service
public class PlatformManagerServiceImpl implements PlatformManagerService {
    @Autowired
    private PlatformManagerDao platformManagerDao;

    @Override
    public int login(String pmId, String password) {
        PlatformManager pm = this.platformManagerDao.findById(pmId);
        if (pm == null) {
            return 1;
        }
        if (! pm.getPassword().equals(password)) {
            return 2;
        }
        return 0;
    }

    @Override
    public PlatformManager loadPlatformManagerById(String pmId) {
        return this.platformManagerDao.findById(pmId);
    }

    @Override
    public int updatePass(String pmId, String newPass, Date date) {
        this.platformManagerDao.updatePass(pmId, newPass, date);
        return 0;
    }
}
