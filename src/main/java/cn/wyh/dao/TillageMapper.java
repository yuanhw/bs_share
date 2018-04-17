package cn.wyh.dao;

import cn.wyh.entity.Tillage;

public interface TillageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tillage record);

    int insertSelective(Tillage record);

    Tillage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tillage record);

    int updateByPrimaryKey(Tillage record);
}