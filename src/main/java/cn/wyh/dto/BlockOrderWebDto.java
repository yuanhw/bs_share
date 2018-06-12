package cn.wyh.dto;

import java.io.Serializable;

/**
 * Created by WYH on 2018/4/9.
 */
public class BlockOrderWebDto implements Serializable {
    private String orderId;
    private int status;
    private String userPhone;
    private int farmManagerId;
    private int currentPage;
    private int startIndex;
    private int number;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getFarmManagerId() {
        return farmManagerId;
    }

    public void setFarmManagerId(int farmManagerId) {
        this.farmManagerId = farmManagerId;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
