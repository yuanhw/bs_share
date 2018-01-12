package cn.wyh.web;

import cn.wyh.entity.FarmManager;
import cn.wyh.service.FarmManagerService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by WYH on 2017/12/18.
 */
@RestController
@RequestMapping("/farmManager")
public class FarmManagerController {
    @Autowired
    private FarmManagerService farmService;

    @RequestMapping("/loadObj")
    public String loadObj(@RequestParam  String phone) {
        //System.out.println("# FarmManagerController # loadObj # " + phone);
        FarmManager fm = this.farmService.loadObjByPhone(phone);
        JSONObject jsonObject = new JSONObject();
        if (fm != null) {
            jsonObject.put("status", 0);
            jsonObject.put("rt", fm);
        } else {
            jsonObject.put("status", 1);
        }
        //System.out.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

    @RequestMapping("/reg")
    public String reg(@ModelAttribute FarmManager farm) {
        farm.setRegTime(new Date());
        farm.setCheckStatus(0);
        this.farmService.reg(farm);
        return "{\"status\": 0}";
    }
    @RequestMapping("/loadData")
    public String loadData(@RequestParam String phone, @RequestParam String fmName,
                           @RequestParam int status, @RequestParam int currentPage) {
        //System.out.println(phone + " , " + fmName + ", " + status);
        int start = (currentPage - 1) * 5;
        int total = this.farmService.totalNums(phone, fmName, status);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", total);
        jsonObject.put("list", this.farmService.loadData(phone, fmName, status, start));
        String str = jsonObject.toJSONString();
        //System.out.println(str);
        return str;
    }

    @RequestMapping("/sh")
    public String sh(@RequestParam String phone, @RequestParam int status) {
        //System.out.println("/sh " + phone +" " + status);
        int rt_status = 1;
        //FarmManager farmManager = this.farmService.loadObjByPhone(phone);
        farmService.updateStatus(status, phone);
        //System.out.println("/sh , rt_status = " + rt_status);
        return "{\"status\":" + rt_status + " }";
    }
    @RequestMapping("/del")
    public String delete(@RequestParam String phone) {
        this.farmService.deleteByPhone(phone);
        return "{\"status\": 1}";
    }
}
