package cn.wyh.dto;

import cn.wyh.entity.CaiOrder;

import java.io.Serializable;

/**
 * Created by WYH on 2018/4/21.
 */
public class CaiOrderWeb extends CaiOrder implements Serializable {
    private String greenTitle;
    private String greenImg;
    private String statusValue;
    private Integer status;

    public String getGreenTitle() {
        return greenTitle;
    }

    public void setGreenTitle(String greenTitle) {
        this.greenTitle = greenTitle;
    }

    public String getGreenImg() {
        return greenImg;
    }

    public void setGreenImg(String greenImg) {
        this.greenImg = greenImg;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
        switch (status) {
            case 0:
                this.setStatusValue("待受理");
                break;
            case 1:
                this.setStatusValue("已受理");
                break;
            case 2:
                this.setStatusValue("已配送");
                break;
            case 3:
                this.setStatusValue("已完成");
                break;
        }
    }
}
