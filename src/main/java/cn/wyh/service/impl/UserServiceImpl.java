package cn.wyh.service.impl;

import cn.wyh.dao.UserDao;
import cn.wyh.entity.User;
import cn.wyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by WYH on 2018/1/2.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean reg(User user) {
        try {
            this.userDao.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User findByPhone(String phone) {
        return this.userDao.findByPhone(phone);
    }

    @Override
    public List<User> loadAll() {
        return this.userDao.findAll();
    }

    @Override
    public void updateLoginTime(Date date, String phone) {
        this.userDao.updateLoginTime(date, phone);
    }
}
