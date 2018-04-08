package cn.wyh.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/4/7.
 */
public class BlockOrderItem implements Serializable {
    /**
     * `b_order_id` varchar(255) NOT NULL COMMENT '订单编号',
     * `b_detail_id` int(11) NOT NULL COMMENT '地块编号',
     * `create_time` datetime NOT NULL COMMENT '创建时间',
     */
    private String bOrderId;
    private String bDetailId;
    private Date createTime;

    public BlockOrderItem(String bOrderId, String bDetailId, Date createTime) {
        this.bOrderId = bOrderId;
        this.bDetailId = bDetailId;
        this.createTime = createTime;
    }

    public String getbOrderId() {
        return bOrderId;
    }

    public void setbOrderId(String bOrderId) {
        this.bOrderId = bOrderId;
    }

    public String getbDetailId() {
        return bDetailId;
    }

    public void setbDetailId(String bDetailId) {
        this.bDetailId = bDetailId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
