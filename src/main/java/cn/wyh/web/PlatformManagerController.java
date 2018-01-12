package cn.wyh.web;

import cn.wyh.entity.PlatformManager;
import cn.wyh.service.PlatformManagerService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by WYH on 2017/12/13.
 */
@Controller
@RequestMapping("/platformManager")
public class PlatformManagerController {
    @Autowired
    private PlatformManagerService platformManagerService;

    @ResponseBody
    @RequestMapping("/login")
    public String pmLogin(@RequestParam String pmId, @RequestParam String password) {
        int status = this.platformManagerService.login(pmId, password);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        //System.out.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping("/loadObj")
    public String loadObj(@RequestParam String pmId) {
        PlatformManager platformManager = this.platformManagerService.loadPlatformManagerById(pmId);
        int status = 0;
        if (platformManager == null) {
            status = 3;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        jsonObject.put("rt", platformManager);
        //System.out.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping("/updatePass")
    public String updatePass(@RequestParam String pmId, @RequestParam String newPass) {
        this.platformManagerService.updatePass(pmId, newPass, new Date());
        return "{\"status\": 0}";
    }
}
