package cn.wyh.dao;

import cn.wyh.entity.PlatformManager;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface PlatformManagerDao {
    PlatformManager findById(String pmId);
    void updatePass(@Param("pmId") String pmId, @Param("newPass") String newPass, @Param("upTime") Date upTime);
}
