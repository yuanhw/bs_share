package cn.wyh.dao;

import cn.wyh.entity.Green;

import java.util.List;

public interface GreenDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Green record);

    int insertSelective(Green record);

    Green selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Green record);

    int updateByPrimaryKey(Green record);

    List<Green> selectList();
}