package cn.wyh.dao;

import cn.wyh.dto.UpdateFarmManager;
import cn.wyh.entity.FarmManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by WYH on 2017/12/18.
 */
public interface FarmManagerDao {
    FarmManager findByPhone(String phone);
    void addObj(FarmManager farmManager);
    void updateStatus(@Param("status") int status, @Param("phone") String phone);
    int totalRowNumByConditions(
            @Param("phone") String phone,
            @Param("fmName") String fmName,
            @Param("status") int status);

    List<FarmManager> findList(
            @Param("phone") String phone,
            @Param("fmName") String fmName,
            @Param("status") int status,
            @Param("start") int start);
    void deleteByPhone(@Param("phone") String phone);
    void updateFM(UpdateFarmManager farmManager);

    void updateFMAccount(@Param("account") double account, @Param("phone") String phone);

    FarmManager findById(int id);
}
