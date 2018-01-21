package cn.wyh.web;

import cn.wyh.service.ProvinceService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WYH on 2018/1/21.
 */
@RestController
@RequestMapping("/const")
public class ConstController {
    @Autowired
    private ProvinceService provinceService;

    @RequestMapping("/loadProvince")
    public String loadProvinceList() {
        return JSON.toJSONString(this.provinceService.loadListProvince());
    }
}
