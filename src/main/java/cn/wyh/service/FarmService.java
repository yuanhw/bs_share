package cn.wyh.service;

import cn.wyh.dto.LateLySimplyFarm;
import cn.wyh.entity.Farm;

import java.util.List;

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
    List<LateLySimplyFarm> selectFarmByPos(Double lat, Double lng);
}
