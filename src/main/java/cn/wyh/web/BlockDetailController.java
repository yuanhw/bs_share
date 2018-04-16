package cn.wyh.web;

import cn.wyh.common.response.ResponseSuccess;
import cn.wyh.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WYH on 2018/4/13.
 */
@RestController
@RequestMapping("/block/app")
public class BlockDetailController {
    @Autowired
    private BlockService blockService;

    @RequestMapping("/loadBLock")
    public String loadBLock(@RequestParam int userId, @RequestParam int type) {
        return new ResponseSuccess(blockService.loadDetailForApp(userId, type)).toString();
    }
}
