package cn.wyh.web;

import cn.wyh.common.response.ResponseSuccess;
import cn.wyh.service.BlockPlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WYH on 2018/4/15.
 */
@RestController
@RequestMapping("/plant")
public class BlockPlantController {
    @Autowired
    private BlockPlantService blockPlantService;

    @RequestMapping("/app/loadGreenList")
    public String loadGreenList() {
        return new ResponseSuccess(blockPlantService.loadGreenList()).toString();
    }

    @RequestMapping("/app/addPlantOrder")
    public String addPlantOrder(@RequestParam Integer blockId, @RequestParam Integer greenId) {
        return new ResponseSuccess(blockPlantService.addPlantOrder(blockId, greenId)).toString();
    }

    @RequestMapping("/app/currentPlantStatus")
    public String currentPlantStatus(@RequestParam Integer blockId) {
        return new ResponseSuccess(blockPlantService.currentPlantStatus(blockId)).toString();
    }
}
