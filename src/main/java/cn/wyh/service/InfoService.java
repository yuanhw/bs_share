package cn.wyh.service;

import cn.wyh.entity.Info;

import java.util.List;

/**
 * Created by WYH on 2018/4/22.
 */
public interface InfoService {
    int updateUserToken(int userId, String token);

    List<Info> loadInfoList(int userId);

    int setHasRead(int id);

    int delInfo(int id);
}
