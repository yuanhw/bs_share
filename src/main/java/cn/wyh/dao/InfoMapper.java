package cn.wyh.dao;

import cn.wyh.entity.Info;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Info record);

    int insertSelective(Info record);

    Info selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKey(Info record);

    List<Info> selectInfoListByUserId(@Param("userId") int userId);
}