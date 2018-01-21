package cn.wyh.service;

import cn.wyh.dto.UpdateFarmManager;
import cn.wyh.entity.FarmManager;

import java.util.List;

/**
 * Created by WYH on 2017/12/18.
 */
public interface FarmManagerService {
    FarmManager loadObjByPhone(String phone);
    void reg(FarmManager farmManager);
    void updateStatus(int status, String phone);
    List<FarmManager> loadData(String phone, String fmName, int status, int start);
    int totalNums(String phone, String fmName, int status);
    void deleteByPhone(String phone);
    void updateFM(UpdateFarmManager farmManager);
}
