package cn.wyh.dto;

import java.io.Serializable;

/**
 * Created by WYH on 2018/4/17.
 */
public class BlockPlantWebDto extends BlockPlantDto implements Serializable {
    private Integer greenLife;
    private String statusValue;
    private int status;

    public Integer getGreenLife() {
        return greenLife;
    }

    public void setGreenLife(Integer greenLife) {
        this.greenLife = greenLife;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        if (status == 1) {
            setStatusValue("待受理");
        } else if (status == 2) {
            setStatusValue("已受理");
        }
    }
}
