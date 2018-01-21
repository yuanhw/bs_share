package cn.wyh.service;

import cn.wyh.dao.ProvinceDao;

import java.util.List;

/**
 * Created by WYH on 2018/1/21.
 */
public interface ProvinceService {
    List<ProvinceDao> loadListProvince();
}
