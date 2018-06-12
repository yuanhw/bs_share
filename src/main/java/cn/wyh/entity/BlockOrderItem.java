package cn.wyh.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/4/7.
 */
public class BlockOrderItem implements Serializable {
    private String borderId;
    private String bdetailId;
    private Date createTime;

    public BlockOrderItem(String bOrderId, String bDetailId, Date createTime) {
        this.borderId = bOrderId;
        this.bdetailId = bDetailId;
        this.createTime = createTime;
    }

    public BlockOrderItem() {
    }

    public String getBorderId() {
        return borderId;
    }

    public void setBorderId(String borderId) {
        this.borderId = borderId;
    }

    public String getBdetailId() {
        return bdetailId;
    }

    public void setBdetailId(String bdetailId) {
        this.bdetailId = bdetailId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
