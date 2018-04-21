package cn.wyh.service;

import cn.wyh.dto.CaiOneDto;
import cn.wyh.dto.CaiSearchDto;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by WYH on 2018/4/20.
 */
public interface CaiService {
    int createCaiOrder(int plantId, int userId);

    int isHasCaiOrder(int plantId);

    List<CaiOneDto> loadCaiList(int userId, int status);

    int isHasDefaultAddress(int userId);

    JSONObject loadCaiListForWeb(CaiSearchDto search);

    int processCai(int caiId, String operator, String phone);

    int updateStatus(int id, int status);
}
