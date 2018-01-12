package cn.wyh.web;

import cn.wyh.entity.User;
import cn.wyh.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        int  tag = 1;
        if (!this.userService.reg(user)) {
            tag = 0;
        }
        return "{\"status\": 1, \"tag\": " + tag + "}";
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
