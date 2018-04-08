package cn.wyh.dto;

import cn.wyh.entity.BlockDetail;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/4/8.
 */
public class UpdateUserOrder extends BlockDetail implements Serializable {
    private int id;

    public UpdateUserOrder(int userId, int type, String lease, Date updateTime, int status, String branchNo, int id) {
        this.id = id;
        setUserId(userId);
        setType(type);
        setLease(lease);
        setUpdateTime(updateTime);
        setStatus(status);
        setBranchNo(branchNo);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
