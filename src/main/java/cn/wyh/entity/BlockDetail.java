package cn.wyh.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/3/9.
 */
public class BlockDetail implements Serializable {
    private int id;
    private String name;
    private String branchNo;
    private int userId;
    private int tillId;
    private int type;
    private int updateMark;
    private String lease;
    private Date crateTime;
    private Date updateTime;
    private int status;

    public BlockDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTillId() {
        return tillId;
    }

    public void setTillId(int tillId) {
        this.tillId = tillId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUpdateMark() {
        return updateMark;
    }

    public void setUpdateMark(int updateMark) {
        this.updateMark = updateMark;
    }

    public String getLease() {
        return lease;
    }

    public void setLease(String lease) {
        this.lease = lease;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BlockDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", branchNo='" + branchNo + '\'' +
                ", userId=" + userId +
                ", tillId=" + tillId +
                ", type=" + type +
                ", updateMark=" + updateMark +
                ", lease='" + lease + '\'' +
                ", crateTime=" + crateTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}
