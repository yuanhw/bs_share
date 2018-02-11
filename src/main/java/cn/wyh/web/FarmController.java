package cn.wyh.web;

import cn.wyh.common.CodeError;
import cn.wyh.common.CodeMsg;
import cn.wyh.common.Global;
import cn.wyh.common.response.Response;
import cn.wyh.common.response.ResponseFail;
import cn.wyh.common.response.ResponseSuccess;
import cn.wyh.entity.Farm;
import cn.wyh.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * Created by WYH on 2018/2/7.
 */
@RestController
@RequestMapping("/farm")
public class FarmController {
    @Autowired
    private FarmService farmService;

    @RequestMapping("/loadFarmById")
    public String loadFarmByFmId(@RequestParam String fmId) {
        Farm fm = this.farmService.loadFarmByFmId(fmId);
        if (fm == null) {
            Response response = new Response(null);
            response.setStatus(CodeError.RESPONSE_FAIL);
            response.setMsg(CodeMsg.RESPONSE_NULL_VAL);
            return response.toString();
        }
        ResponseSuccess resp = new ResponseSuccess(fm);
        return resp.toString();
    }

    @RequestMapping("/isHaveFarm")
    public String isHaveFarm(@RequestParam String fmId) {
        Integer tag = this.farmService.isHaveFarmById(fmId);
        ResponseSuccess resp = new ResponseSuccess(tag);
        return resp.toString();
    }

    @RequestMapping("/addFarmBase")
    public String addFarmBase(@ModelAttribute Farm farm) {
        farm.setCreateTime(new Date());
        int i = this.farmService.addFarmBaseInfo(farm);
        if (i == 1) {
            return (new ResponseSuccess(null)).toString();
        } else {
            return (new ResponseFail(null)).toString();
        }
    }

    @RequestMapping("/updateBase")
    public String updateFarmBase(@ModelAttribute Farm farm) {
        int i = this.farmService.updateFarmBaseInfo(farm);
        if (i >= 1) {
            return (new ResponseSuccess(null)).toString();
        } else {
            return (new ResponseFail(null)).toString();
        }
    }

    @RequestMapping("/updateStatus")
    public String updateStatus(@RequestParam Integer code, @RequestParam Integer id) {
        int i = this.farmService.updateStatus(code, id);
        if (i >= 1) {
            return (new ResponseSuccess(null)).toString();
        } else {
            return (new ResponseFail(null)).toString();
        }
    }

    @RequestMapping("/upLoadImg")
    public void upLoadImg(@RequestParam("id") Integer id, @RequestParam("imgFile") MultipartFile imgFile) {
        try {
            if (!imgFile.isEmpty()) {
                System.out.println(imgFile.getOriginalFilename() + " @ " + id);
                File baseUri = new File(Global.baseUri + "farmImg");
                if (!baseUri.exists()) {
                    baseUri.mkdir();
                }
                int tag = this.farmService.updateImg("/farmImg/" +id + imgFile.getOriginalFilename(), id);
                if (tag == 0) {
                    throw new Exception("error");
                }
                imgFile.transferTo(new File(Global.baseUri + "farmImg" + File.separator + id + imgFile.getOriginalFilename()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
