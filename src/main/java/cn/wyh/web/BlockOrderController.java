package cn.wyh.web;

import cn.wyh.common.response.ResponseSuccess;
import cn.wyh.dto.BlockOrderWebDto;
import cn.wyh.dto.ShareOrderDto;
import cn.wyh.entity.Refund;
import cn.wyh.entity.User;
import cn.wyh.service.BlockOrderService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        int code = 0;
        try {
            code = this.blockOrderService.createBlockOrder(user, obj);
        } catch (Exception e) {
            code = 0;
        }
        return new ResponseSuccess(code).toString();
    }

    @RequestMapping("/getAllOrderSInfo")
    public String getAllOrderSInfoByUserId(@RequestParam String userId, @RequestParam int status) {
        return new ResponseSuccess(this.blockOrderService.loadTabOrderList1(userId, status)).toString();
    }

    @RequestMapping("/loadOrderWeb")
    public String loadOrderWeb(@ModelAttribute BlockOrderWebDto dto) {
        return new ResponseSuccess(this.blockOrderService.loadOrderWeb(dto)).toString();
    }

    @RequestMapping("/applyRefund")
    public String applyRefund(@ModelAttribute Refund refund) {
        return new ResponseSuccess(this.blockOrderService.applyRefund(refund)).toString();
    }

    @RequestMapping("/loadRefundList")
    public String loadRefundList(@RequestParam Integer userId) {
        return new ResponseSuccess(blockOrderService.loadRefundList(userId)).toString();
    }

    @RequestMapping("/cancelRefund")
    public String cancelRefund(@RequestParam String orderId) {
        return new ResponseSuccess(blockOrderService.cancelRefund(orderId)).toString();
    }

    @RequestMapping("/heXiao")
    public String heXiao(@RequestParam String orderId) {
        return new ResponseSuccess(this.blockOrderService.heXiao(orderId)).toString();
    }

    @RequestMapping("/delRefund")
    public String delRefund(@RequestParam String orderId) {
        return new ResponseSuccess(this.blockOrderService.delRefund(orderId)).toString();
    }

    @RequestMapping("/delOrder")
    public String delOrder(@RequestParam String orderId) {
        return new ResponseSuccess(blockOrderService.delOrder(orderId)).toString();
    }

    @RequestMapping("/orderComment")
    public String orderComment(@RequestParam String orderId,
                               @RequestParam int rating, @RequestParam String comment) {
        return new ResponseSuccess(blockOrderService.orderComment(orderId, rating, comment)).toString();
    }

    @RequestMapping("/delOrderWeb")
    public String delOrderWeb(@RequestParam String orderId) {
        return new ResponseSuccess(this.blockOrderService.delOrderWeb(orderId)).toString();
    }

    @RequestMapping("/isCanRefund")
    public String isCanRefund(@RequestParam String orderId) {
        return new ResponseSuccess(this.blockOrderService.isCanRefund(orderId)).toString();
    }

    @RequestMapping("/loadDetailSimple")
    public String loadDetailSimple(@RequestParam String orderId) {
        return new ResponseSuccess(this.blockOrderService.loadSimpleDetail(orderId)).toString();
    }

    @RequestMapping("/getRefund")
    public String getRefund(@RequestParam String orderId) {
        return new ResponseSuccess(blockOrderService.getRefund(orderId)).toString();
    }

    @RequestMapping("/processRefund")
    public String processRefund(@RequestParam String orderId, @RequestParam int status) {
        return new ResponseSuccess(blockOrderService.processRefund(orderId, status)).toString();
    }
}
