package cn.wyh.dao;

import cn.wyh.entity.TillageImgList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TillageImgListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TillageImgList record);

    int insertSelective(TillageImgList record);

    TillageImgList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TillageImgList record);

    int updateByPrimaryKey(TillageImgList record);

    List<String> selectImgListByTillageId(@Param("tillageId") int tillageId);
}