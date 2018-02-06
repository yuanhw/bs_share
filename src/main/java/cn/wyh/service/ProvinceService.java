package cn.wyh.service;

import cn.wyh.dao.ProvinceDao;
import cn.wyh.entity.City;

import java.util.List;

/**
 * Created by WYH on 2018/1/21.
 */
public interface ProvinceService {
    List<ProvinceDao> loadListProvince();
    List<City> loadCityList();
}
