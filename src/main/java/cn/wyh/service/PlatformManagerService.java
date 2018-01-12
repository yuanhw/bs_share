package cn.wyh.service;

import cn.wyh.entity.PlatformManager;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by WYH on 2017/12/13.
 */
public interface PlatformManagerService {
    int login(String pmId, String password); //返回状态码
    PlatformManager loadPlatformManagerById(String pmId);
    int updatePass(String pmId, String newPass, Date date);
}
