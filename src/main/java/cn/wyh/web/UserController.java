package cn.wyh.web;

import cn.wyh.common.Global;
import cn.wyh.common.response.ResponseSuccess;
import cn.wyh.entity.Address;
import cn.wyh.entity.User;
import cn.wyh.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
        User user = this.userService.findByPhone(phone);
        if (user != null) {
            tag = 1;
        }
        return "{\"status\": 1, \"tag\": " + tag + "}";
    }
    @RequestMapping("/reg")
    public String reg(@RequestParam String jsonStr) {
        //System.out.println(jsonStr);
        User user = JSON.parseObject(jsonStr, User.class);
        //System.out.println(user);
        int status = 1;
        String msg = "注册成功";
        user.setRegTime(new Date());
        user.setTouImgPath(Global.DEFAULT_IMGPATH);
        user.setPayPassword("E10ADC3949BA59ABBE56E057F20F883E");
        if (!this.userService.reg(user)) {
            status = 0;
            msg = "注册失败";
        }
        return "{\"status\": " + status + ", \"msg\": \"" + msg + "\"}";
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

    @RequestMapping("/delete")
    public String delete(@RequestParam int id) {
        int status = 1;
        String msg = "删除成功";
        if (!this.userService.deleteUser(id)) {
            status = 0;
            msg = "删除失败";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        jsonObject.put("msg", msg);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/login")
    public String login(@RequestParam String jsonStr) {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        int status = 1;
        String msg = "加载成功";
        JSONObject jsonObject2 = new JSONObject();
        User user = this.userService.findByPhone(jsonObject.getString("phone"));
        if (user == null) {
            status = 0;
            msg = "账号不存在";
        } else if (!user.getPassword().equals(jsonObject.getString("password"))) {
            status = 0;
            msg = "密码错误";
        } else {
            this.userService.updateLoginTime(new Date(), jsonObject.getString("phone"));
            jsonObject2.put("user", user);
        }
        jsonObject2.put("status", status);
        jsonObject2.put("msg", msg);
        //System.out.println("UserController " + jsonObject2.toJSONString());
        return jsonObject2.toJSONString();
    }

    @RequestMapping("/uploadImg")
    public String uploadImg(@RequestParam("phone") String phone, @RequestParam("imgFile") MultipartFile imgFile) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (!imgFile.isEmpty()) {
                System.out.println(Global.baseUri);
                File baseUri = new File(Global.baseUri + "userImg");
                if (!baseUri.exists()) {
                    baseUri.mkdir();
                }
                boolean tag = this.userService.updateImg("/userImg/" + imgFile.getOriginalFilename(), phone);
                if (!tag) {
                    throw new Exception("error");
                }
                imgFile.transferTo(new File(Global.baseUri + "userImg" + File.separator + "" + imgFile.getOriginalFilename()));
                jsonObject.put("status", 1);
                jsonObject.put("msg", "上传成功");
            }
        } catch (Exception e) {
            jsonObject.put("status", 0);
            jsonObject.put("msg", "上传失败");
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping("/app/addAddress")
    public String addAddress(@ModelAttribute Address address) {
        return new ResponseSuccess(userService.addAddress(address)).toString();
    }

    @RequestMapping("/app/loadAddressList")
    public String loadAddressList(@RequestParam Integer userId) {
        return new ResponseSuccess(userService.loadAddressList(userId)).toString();
    }

    @RequestMapping("/app/delAddress")
    public String delAddress(@RequestParam Integer id) {
        return new ResponseSuccess(userService.delAddress(id)).toString();
    }
}
