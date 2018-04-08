package cn.wyh.web;

import cn.wyh.common.response.ResponseSuccess;
import cn.wyh.dto.ShareOrderDto;
import cn.wyh.entity.User;
import cn.wyh.service.BlockOrderService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WYH on 2018/4/6.
 */
@RestController
@RequestMapping("/block/order")
public class BlockOrderController {
    @Autowired
    private BlockOrderService blockOrderService;

    @RequestMapping("/getShareOrder")
    public String getShareOrderInfo(@RequestParam String batchNo) {
        return new ResponseSuccess(this.blockOrderService.getShareOrderByBatchNo(batchNo)).toString();
    }

    @RequestMapping("/submitBlockOrder")
    public String createOrder(@RequestParam String jsonStr) {
        JSONObject json = JSONObject.parseObject(jsonStr, JSONObject.class);
        User user = json.getObject("user", User.class);
        ShareOrderDto obj = json.getObject("shareOrder", ShareOrderDto.class);
        int code = 1;
        try {
            this.blockOrderService.createBlockOrder(user, obj);
        } catch (Exception e) {
            code = 0;
        }
        return new ResponseSuccess(code).toString();
    }
}
