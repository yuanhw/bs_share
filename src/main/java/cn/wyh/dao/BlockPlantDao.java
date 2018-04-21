package cn.wyh.dao;

import cn.wyh.dto.*;
import cn.wyh.entity.BlockPlant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlockPlantDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BlockPlant record);

    int insertSelective(BlockPlant record);

    BlockPlant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlockPlant record);

    int updateByPrimaryKey(BlockPlant record);

    int insertBackId(BlockPlant record);

    CurrentStatus selectCurrentStatusById(@Param("id") int id);

    List<BlockPlantDto> selectPlantStatus(@Param("userId") int userId, @Param("status") int status);

    List<BlockPlantWebDto> selectPlantForWeb(BlockPlantSearch search);

    int getPlantForWebTotal(BlockPlantSearch search);

    int updateProcess(BlockPlantDto dto);

    List<PlantStatusWebDto> selectStatus(BlockPlantSearch search);

    int getTotal2(BlockPlantSearch search);

    int getFarmManagerIdByPlantId(@Param("id") int id);
}