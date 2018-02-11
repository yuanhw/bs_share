package cn.wyh.service;

import cn.wyh.entity.Farm;

/**
 * Created by WYH on 2018/2/7.
 */
public interface FarmService {
    Farm loadFarmByFmId(String fmId);
    int isHaveFarmById(String fmId);
    int addFarmBaseInfo(Farm farm);
    int updateFarmBaseInfo(Farm farm);
    int updateStatus(int code, Integer id);
    int updateImg(String imgFile, Integer id);
}
