package cn.wyh.web;

import cn.wyh.common.response.ResponseSuccess;
import cn.wyh.dto.CaiSearchDto;
import cn.wyh.service.CaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WYH on 2018/4/20.
 */
@RestController
@RequestMapping("/cai")
public class CaiController {
    @Autowired
    private CaiService caiService;

    @RequestMapping("/app/createCaiOrder")
    public String createCaiOrder(@RequestParam Integer plantId, @RequestParam Integer userId) {
        return new ResponseSuccess(caiService.createCaiOrder(plantId, userId)).toString();
    }

    @RequestMapping("/app/isHasCaiOrder")
    public String isHasCaiOrder(@RequestParam Integer plantId) {
        return new ResponseSuccess(caiService.isHasCaiOrder(plantId)).toString();
    }

    @RequestMapping("/app/loadCaiOrderByStatus")
    public String loadCaiOrderByStatus(@RequestParam Integer userId, @RequestParam Integer status) {
        return new ResponseSuccess(caiService.loadCaiList(userId, status)).toString();
    }

    @RequestMapping("/app/isHasDefaultAddress")
    public String isHasDefaultAddress(@RequestParam Integer userId) {
        return new ResponseSuccess(caiService.isHasDefaultAddress(userId)).toString();
    }

    @RequestMapping("/web/loadCaiOrderList")
    public String loadCaiOrderForWeb(@ModelAttribute CaiSearchDto search) {
        return new ResponseSuccess(caiService.loadCaiListForWeb(search)).toString();
    }

    @RequestMapping("/web/processCai")
    public String processCai(@RequestParam Integer caiId,
                             @RequestParam String operator,
                             @RequestParam String phone) {
        return new ResponseSuccess(caiService.processCai(caiId, operator, phone)).toString();
    }

    @RequestMapping("/web/updateStatus")
    public String updateStatus(@RequestParam Integer id, @RequestParam Integer status) {
        return new ResponseSuccess(caiService.updateStatus(id, status)).toString();
    }
}
