package cn.wyh.service.impl;

import cn.wyh.dao.InfoMapper;
import cn.wyh.dao.UserTokenMapper;
import cn.wyh.entity.Info;
import cn.wyh.entity.UserToken;
import cn.wyh.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by WYH on 2018/4/22.
 */
@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private InfoMapper infoMapper;

    @Override
    public int updateUserToken(int userId, String token) {
        UserToken token0 = userTokenMapper.selectUserTokenByUserId(userId);
        if (token0 == null) {
            UserToken token1 = new UserToken();
            token1.setUserId(userId);
            token1.setDeviceToken(token);
            token1.setUpdateTime(new Date());
            userTokenMapper.insertSelective(token1);
        } else {
            userTokenMapper.updateTokenByUserId(userId, token);
        }
        return 1;
    }

    @Override
    public List<Info> loadInfoList(int userId) {
        return infoMapper.selectInfoListByUserId(userId);
    }

    @Override
    public int setHasRead(int id) {
        Info info = new Info();
        info.setId(id);
        info.setUpdateTime(new Date());
        info.setHasRead(1);
        infoMapper.updateByPrimaryKeySelective(info);
        return 1;
    }

    @Override
    public int delInfo(int id) {
        return infoMapper.deleteByPrimaryKey(id);
    }
}
