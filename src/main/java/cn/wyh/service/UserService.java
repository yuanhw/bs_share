package cn.wyh.service;

import cn.wyh.entity.Address;
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
    boolean deleteUser(int id);
    boolean updateImg(String imgPath, String phone);
    boolean updateAccount(double account, String phone);
    boolean updatePayPass(String payPass, String phone);

    int addAddress(Address address);

    List<Address> loadAddressList(int userId);

    int delAddress(int id);
}
