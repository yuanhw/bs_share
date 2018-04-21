package cn.wyh.service.impl;

import cn.wyh.dao.AddressMapper;
import cn.wyh.dao.UserDao;
import cn.wyh.entity.Address;
import cn.wyh.entity.User;
import cn.wyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by WYH on 2018/1/2.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AddressMapper addressMapper;

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

    @Override
    public boolean deleteUser(int id) {
        boolean tag = true;
        try {
            this.userDao.delete(id);
        } catch (Exception e) {
            tag = false;
            e.printStackTrace();
        } finally {
            return tag;
        }
    }

    @Override
    public boolean updateImg(String imgPath, String phone) {
        boolean tag = true;
        try {
            this.userDao.updateImg(imgPath, phone);
        } catch (Exception e) {
            tag = false;
            e.printStackTrace();
        } finally {
            return tag;
        }
    }

    @Override
    public boolean updateAccount(double account, String phone) {
        boolean tag = true;
        try {
            this.userDao.updateAccount(account, phone);
        } catch (Exception e) {
            tag = false;
            e.printStackTrace();
        } finally {
            return tag;
        }
    }

    @Override
    public boolean updatePayPass(String payPass, String phone) {
        boolean tag = true;
        try {
            this.userDao.updatePayPass(payPass, phone);
        } catch (Exception e) {
            tag = false;
            e.printStackTrace();
        } finally {
            return tag;
        }
    }

    @Transactional
    @Override
    public int addAddress(Address address) {
        try {
            if (address.getSing() == 1) {
                addressMapper.updateSingByUserId(address.getUserId());
            }
            addressMapper.insertSelective(address);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public List<Address> loadAddressList(int userId) {
        return addressMapper.selectByUserId(userId);
    }

    @Override
    public int delAddress(int id) {
        return addressMapper.deleteByPrimaryKey(id);
    }
}
