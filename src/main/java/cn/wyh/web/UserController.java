package cn.wyh.web;

import cn.wyh.entity.User;
import cn.wyh.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by WYH on 2018/1/2.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/check")
    public String checkPhone(@RequestParam String phone) {
        int tag = 0;
        User user = this.userService.findByPhone("phone");
        if (user != null) {
            tag = 1;
        }
        return "{\"status\": 1, \"tag\": " + tag + "}";
    }

    @RequestMapping("/reg")
    public String reg(@ModelAttribute User user) {
        int status = 1;
        String msg = "注册成功";
        try {
            user.setRegTime(new Date());
            if (!this.userService.reg(user)) {
                status = 0;
                msg = "注册失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = 0;
            msg = "服务器错误";
        } finally {
            return "{\"status\": " + status + ", \"msg\": \"" + msg + "\"}";
        }
    }

    @RequestMapping("/findByPhone")
    public String findByPhone(@RequestParam String phone) {
        User user = this.userService.findByPhone(phone);
        return JSON.toJSONString(user);
    }

    @RequestMapping("/findAll")
    public String findAll() {
        return JSON.toJSONString(this.userService.loadAll());
    }
}
