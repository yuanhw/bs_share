package cn.wyh.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/4/7.
 */
public class BlockOrder implements Serializable {
    private String orderId;
    private Date createDate;
    private Date validDate;
    private Date noValidDate;
    private int number;
    private String spec;
    private String amount;
    private int time;
    private int userId;
    private int farmManagerId;
    private String batchNo;
    private int status;
    private int type;

    private Integer grade;
    private String comment;

    public BlockOrder() {
    }

    public BlockOrder(String orderId, Date createDate, Date validDate, Date noValidDate,
                      int number, String spec, String amount, int time, int userId,
                      int farmManagerId, String batchNo, int status, int type) {
        this.orderId = orderId;
        this.createDate = createDate;
        this.validDate = validDate;
        this.noValidDate = noValidDate;
        this.number = number;
        this.spec = spec;
        this.amount = amount;
        this.time = time;
        this.userId = userId;
        this.farmManagerId = farmManagerId;
        this.batchNo = batchNo;
        this.status = status;
        this.type = type;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getNoValidDate() {
        return noValidDate;
    }

    public void setNoValidDate(Date noValidDate) {
        this.noValidDate = noValidDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFarmManagerId() {
        return farmManagerId;
    }

    public void setFarmManagerId(int farmManagerId) {
        this.farmManagerId = farmManagerId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String convertStatus() {
        if (this.getNoValidDate().getTime() - new Date().getTime() < 0) {
            return "待核销";
        }
        switch (status) {
            case 0:
                return "交易完成";
            case 1:
                return "正常";
            case 2:
                return "待评价";
            case 3:
                return "退款中";
            case 4:
                return "已退款";
            case 5:
                return "已删除";
            default:
                return "交易完成";
        }
    }

    public String convertType() {
        if (this.status == 0) {
            return "仅自种";
        } else {
            return "可代种";
        }
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BlockOrder{" +
                "orderId='" + orderId + '\'' +
                ", createDate=" + createDate +
                ", validDate=" + validDate +
                ", noValidDate=" + noValidDate +
                ", number=" + number +
                ", spec='" + spec + '\'' +
                ", amount='" + amount + '\'' +
                ", time=" + time +
                ", userId=" + userId +
                ", farmManagerId=" + farmManagerId +
                ", batchNo='" + batchNo + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
