package cn.wyh.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class PlatformManager implements Serializable{
    private String pmId;
    private String password;
    private String pmName;

    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date riseTime;

    public PlatformManager() {
    }

    public PlatformManager(String pmId, String password, String pmName, Date riseTime) {
        this.pmId = pmId;
        this.password = password;
        this.pmName = pmName;
        this.riseTime = riseTime;
    }

    public String getPmId() {
        return pmId;
    }

    public void setPmId(String pmId) {
        this.pmId = pmId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public Date getRiseTime() {
        return riseTime;
    }

    public void setRiseTime(Date riseTime) {
        this.riseTime = riseTime;
    }

    @Override
    public String toString() {
        return "PlatformManager{" +
                "pmId=" + pmId +
                ", password='" + password + '\'' +
                ", pmName='" + pmName + '\'' +
                ", riseTime=" + riseTime +
                '}';
    }
}
