package cn.wyh.web;

import cn.wyh.common.response.ResponseSuccess;
import cn.wyh.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WYH on 2018/4/22.
 */
@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private InfoService infoService;

    @RequestMapping("/app/updateUserToken")
    public String updateToken(@RequestParam Integer userId, @RequestParam String token) {
        return new ResponseSuccess(infoService.updateUserToken(userId, token)).toString();
    }

    @RequestMapping("/app/loadInfoList")
    public String updateToken(@RequestParam Integer userId) {
        return new ResponseSuccess(infoService.loadInfoList(userId)).toString();
    }

    @RequestMapping("/app/setHasRead")
    public String setHasRead(@RequestParam Integer id) {
        return new ResponseSuccess(infoService.setHasRead(id)).toString();
    }

    @RequestMapping("/app/delInfo")
    public String delInfo(@RequestParam Integer id) {
        return new ResponseSuccess(infoService.delInfo(id)).toString();
    }
}
