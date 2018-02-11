package cn.wyh.dao;

import cn.wyh.entity.Farm;
import org.apache.ibatis.annotations.Param;

public interface FarmDao {
    Farm selectFarmById(String id);
    int addFarmBaseInfo(@Param("farm") Farm farm);
    int updateFarmInfo(@Param("farm") Farm farm);
    int updateStatus(@Param("code") int code, @Param("id") Integer id);
    int updateImg(@Param("fmImg") String imgFile, @Param("id") Integer id);
}