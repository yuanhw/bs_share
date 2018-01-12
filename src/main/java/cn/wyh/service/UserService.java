package cn.wyh.service;

import cn.wyh.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by WYH on 2018/1/2.
 */
public interface UserService {
    boolean reg(User user);
    User findByPhone(String phone);
    List<User> loadAll();
    void updateLoginTime(Date date, String phone);
}
