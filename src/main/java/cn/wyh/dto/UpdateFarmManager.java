package cn.wyh.dto;

import java.io.Serializable;

/**
 * Created by WYH on 2018/1/21.
 */
public class UpdateFarmManager implements Serializable{
    private String phone;
    private String fmName;
    private String province;
    private String city;
    private String detailedAddress;
    private String farmSize;

    public UpdateFarmManager() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getFarmSize() {
        return farmSize;
    }

    public void setFarmSize(String farmSize) {
        this.farmSize = farmSize;
    }

    @Override
    public String toString() {
        return "UpdateFarmManager{" +
                "phone='" + phone + '\'' +
                ", fmName='" + fmName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", farmSize='" + farmSize + '\'' +
                '}';
    }
}
