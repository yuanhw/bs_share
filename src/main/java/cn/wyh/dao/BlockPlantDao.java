package cn.wyh.dao;

import cn.wyh.dto.CurrentStatus;
import cn.wyh.entity.BlockPlant;
import org.apache.ibatis.annotations.Param;

public interface BlockPlantDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BlockPlant record);

    int insertSelective(BlockPlant record);

    BlockPlant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlockPlant record);

    int updateByPrimaryKey(BlockPlant record);

    int insertBackId(BlockPlant record);

    CurrentStatus selectCurrentStatusById(@Param("id") int id);
}