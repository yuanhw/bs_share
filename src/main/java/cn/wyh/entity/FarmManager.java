package cn.wyh.entity;

import java.io.Serializable;
import java.util.Date;

public class FarmManager implements Serializable {
    private int fmId;
    private String phone;
    private String password;
    private String fmName;
    private String province;
    private String city;
    private String detailedAddress;
    private int farmType;
    private int farmSize;
    private Date regTime;
    private int checkStatus;

    public FarmManager() {
    }

    public FarmManager(int fmId, String phone, String password, String fmName, String province,
                       String city, String detailedAddress, int farmType, int farmSize, Date regTime, int checkStatus) {
        this.fmId = fmId;
        this.phone = phone;
        this.password = password;
        this.fmName = fmName;
        this.province = province;
        this.city = city;
        this.detailedAddress = detailedAddress;
        this.farmType = farmType;
        this.farmSize = farmSize;
        this.regTime = regTime;
        this.checkStatus = checkStatus;
    }

    public int getFmId() {
        return fmId;
    }

    public void setFmId(int fmId) {
        this.fmId = fmId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFmName() {
        return fmName;
    }

    public void setFmName(String fmName) {
        this.fmName = fmName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public int getFarmType() {
        return farmType;
    }

    public void setFarmType(int farmType) {
        this.farmType = farmType;
    }

    public int getFarmSize() {
        return farmSize;
    }

    public void setFarmSize(int farmSize) {
        this.farmSize = farmSize;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Override
    public String toString() {
        return "FarmManager{" +
                "fmId=" + fmId +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", fmName='" + fmName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", farmType=" + farmType +
                ", farmSize=" + farmSize +
                ", regTime=" + regTime +
                ", checkStatus=" + checkStatus +
                '}';
    }
}
