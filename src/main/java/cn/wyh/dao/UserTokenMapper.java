package cn.wyh.dao;

import cn.wyh.entity.UserToken;
import org.apache.ibatis.annotations.Param;

public interface UserTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserToken record);

    int insertSelective(UserToken record);

    UserToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserToken record);

    int updateByPrimaryKey(UserToken record);

    int updateTokenByUserId(@Param("userId") int userId, @Param("token") String token);

    UserToken selectUserTokenByUserId(@Param("userId") int userId);
}