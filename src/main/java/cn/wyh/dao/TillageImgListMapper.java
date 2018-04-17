package cn.wyh.dao;

import cn.wyh.entity.TillageImgList;

public interface TillageImgListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TillageImgList record);

    int insertSelective(TillageImgList record);

    TillageImgList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TillageImgList record);

    int updateByPrimaryKey(TillageImgList record);
}