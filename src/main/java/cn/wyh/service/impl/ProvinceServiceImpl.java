package cn.wyh.service.impl;

import cn.wyh.dao.ProvinceDao;
import cn.wyh.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WYH on 2018/1/21.
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;

    @Override
    public List<ProvinceDao> loadListProvince() {
        return this.provinceDao.loadProvince();
    }
}
