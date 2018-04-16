package cn.wyh.dto;

import cn.wyh.entity.BlockDetail;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/4/8.
 */
public class UpdateUserOrder extends BlockDetail implements Serializable {

    public UpdateUserOrder(int userId, int type, String lease, Date updateTime, int status, String branchNo) {
        setUserId(userId);
        setType(type);
        setLease(lease);
        setUpdateTime(updateTime);
        setStatus(status);
        setBranchNo(branchNo);
    }
}
