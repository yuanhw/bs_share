package cn.wyh.dao;

import cn.wyh.dto.TillageDto;
import cn.wyh.entity.Tillage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TillageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tillage record);

    int insertSelective(Tillage record);

    Tillage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tillage record);

    int updateByPrimaryKey(Tillage record);

    List<TillageDto> selectListByPlantId(@Param("plantId") int plantId);

    int getTotalByPlantId(@Param("plantId") int plantId);
}