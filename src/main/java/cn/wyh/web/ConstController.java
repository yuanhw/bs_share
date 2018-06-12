package cn.wyh.web;

import cn.wyh.entity.City;
import cn.wyh.service.ProvinceService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/loadCityList")
    public String loadCityList() {
        List<City> list = provinceService.loadCityList();
        City city = new City();
        city.setLabel("不限");
        city.setValue("不限");
        list.add(city);
        return JSON.toJSONString(list);
    }
}
