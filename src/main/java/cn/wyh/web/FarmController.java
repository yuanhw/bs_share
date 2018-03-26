package cn.wyh.web;

import cn.wyh.common.CodeError;
import cn.wyh.common.CodeMsg;
import cn.wyh.common.Global;
import cn.wyh.common.response.Response;
import cn.wyh.common.response.ResponseFail;
import cn.wyh.common.response.ResponseSuccess;
import cn.wyh.dto.LateLySimplyFarm;
import cn.wyh.entity.Farm;
import cn.wyh.service.FarmService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

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
        //System.out.println(farm.toString());
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

    @RequestMapping("/loadLateLyFarm")
    public String loadLateLySimplyFarm(@RequestParam String jsonStr) {
        JSONObject request = JSONObject.parseObject(jsonStr);
        double lat = request.getDouble("lat");
        double lng = request.getDouble("lng");
        List<LateLySimplyFarm> resp = this.farmService.selectFarmByPos(lat, lng);
        ResponseSuccess response = new ResponseSuccess(resp);
        return response.toString();
    }

    @RequestMapping("/loadFarmAppById")
    public String loadFarmById(@RequestParam String jsonStr) {
        JSONObject request = JSONObject.parseObject(jsonStr);
        String id = request.getString("id");
        Farm fm = this.farmService.loadFarmById(id);
        ResponseSuccess response = new ResponseSuccess(fm);
        return response.toString();
    }

    @RequestMapping("/loadFarmListByCondition")
    public String loadFarmListByCondition(
            @RequestParam String sid,
            @RequestParam Integer status) {

        List<Farm> list = this.farmService.selectFarmListByCond(sid, status);
        ResponseSuccess response = new ResponseSuccess(list);
        return response.toString();
    }

    @RequestMapping("/loadSFarmByCity")
    public String loadSimpleFarmByCity(@RequestParam String city) {
        ResponseSuccess res = new ResponseSuccess(this.farmService.selectFarmByCity(city));
        return res.toString();
    }

    @RequestMapping("/loadSFarm2")
    public String loadSimpleFarm2(@RequestParam String city, @RequestParam String query) {
        ResponseSuccess res = new ResponseSuccess(this.farmService.selectFarm2(city, query));
        return res.toString();
    }
}
