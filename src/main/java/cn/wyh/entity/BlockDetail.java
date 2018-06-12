package cn.wyh.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/3/9.
 */
public class BlockDetail implements Serializable {
    private Integer id;
    private String name;
    private String branchNo;
    private Integer userId;
    private Integer tillId;
    private Integer type;
    private Integer updateMark;
    private String lease;
    private Date crateTime;
    private Date updateTime;
    private Integer status;

    public BlockDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTillId() {
        return tillId;
    }

    public void setTillId(Integer tillId) {
        this.tillId = tillId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUpdateMark() {
        return updateMark;
    }

    public void setUpdateMark(Integer updateMark) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
