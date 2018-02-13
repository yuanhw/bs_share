package cn.wyh.dao;

import cn.wyh.dto.LateLySimplyFarm;
import cn.wyh.entity.Farm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FarmDao {
    Farm selectFarmById(String id);
    int addFarmBaseInfo(@Param("farm") Farm farm);
    int updateFarmInfo(@Param("farm") Farm farm);
    int updateStatus(@Param("code") int code, @Param("id") Integer id);
    int updateImg(@Param("fmImg") String imgFile, @Param("id") Integer id);
    List<LateLySimplyFarm> selectFarmByPos(@Param("lat") Double lat, @Param("lng") Double lng);
}