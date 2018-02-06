package cn.wyh.dao;

import cn.wyh.entity.City;

import java.util.List;

/**
 * Created by WYH on 2018/1/21.
 */
public interface ProvinceDao {
    List<ProvinceDao> loadProvince();
    List<City> loadCityList();
}
