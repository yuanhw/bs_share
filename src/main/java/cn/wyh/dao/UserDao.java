package cn.wyh.dao;

import cn.wyh.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by WYH on 2018/1/2.
 */
public interface UserDao {
    User findByPhone(String phone);
    List<User> findAll();
    void addUser(User user);
    void updateLoginTime(@Param("loginTime") Date loginTime, @Param("userPhone") String userPhone);
}
