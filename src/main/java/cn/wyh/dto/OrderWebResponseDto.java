package cn.wyh.dto;

import cn.wyh.entity.BlockOrder;

import java.io.Serializable;

/**
 * Created by WYH on 2018/4/9.
 */
public class OrderWebResponseDto extends BlockOrder implements Serializable {
    private String lease;
    private String userName;
    private String userPhone;
    private String statusValue;

    public String getLease() {
        return lease;
    }

    public void setLease(String lease) {
        this.lease = lease;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    @Override
    public void setStatus(int status) {
        super.setStatus(status);
        this.statusValue = this.convertStatus();
    }
}
