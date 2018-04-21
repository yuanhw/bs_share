package cn.wyh.service.impl;

import cn.wyh.dao.AddressMapper;
import cn.wyh.dao.BlockPlantDao;
import cn.wyh.dao.CaiOrderMapper;
import cn.wyh.dao.UserDao;
import cn.wyh.dto.BlockPlantDto;
import cn.wyh.dto.CaiOneDto;
import cn.wyh.entity.Address;
import cn.wyh.entity.CaiOrder;
import cn.wyh.service.CaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by WYH on 2018/4/20.
 */
@Service
public class CaiServiceImpl implements CaiService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CaiOrderMapper caiOrderMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private BlockPlantDao blockPlantDao;

    @Override
    public int createCaiOrder(int plantId, int userId) {
        CaiOrder caiOrder = new CaiOrder();
        caiOrder.setStatus(0);
        caiOrder.setCreateTime(new Date());
        caiOrder.setUserId(userId);
        caiOrder.setPlantId(plantId);
        int fId = blockPlantDao.getFarmManagerIdByPlantId(plantId);
        caiOrder.setFarmId(fId);
        String address = addressMapper.getDefaultAddressByUserId(userId).getAddress();
        caiOrder.setAddress(address);
        caiOrderMapper.insertSelective(caiOrder);
        return 1;
    }

    @Override
    public int isHasCaiOrder(int plantId) {
        return caiOrderMapper.getNumByPlantId(plantId);
    }

    @Override
    public List<CaiOneDto> loadCaiList(int userId, int status) {
        return caiOrderMapper.selectCaiOrder(userId, status);
    }

    @Override
    public int isHasDefaultAddress(int userId) {
        Address address = addressMapper.getDefaultAddressByUserId(userId);
        if (address != null) {
            return 1;
        }
        return 0;
    }
}
