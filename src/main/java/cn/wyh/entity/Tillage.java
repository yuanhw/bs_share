package cn.wyh.entity;

import java.io.Serializable;
import java.util.Date;

public class Tillage implements Serializable {
    private Integer id;

    private Integer plantId;

    private Integer currentImg;

    private String currentVideo;

    private String growStatus;

    private String lastOperation;

    private Date createTime;

    private Integer sign;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public Integer getCurrentImg() {
        return currentImg;
    }

    public void setCurrentImg(Integer currentImg) {
        this.currentImg = currentImg;
    }

    public String getCurrentVideo() {
        return currentVideo;
    }

    public void setCurrentVideo(String currentVideo) {
        this.currentVideo = currentVideo;
    }

    public String getGrowStatus() {
        return growStatus;
    }

    public void setGrowStatus(String growStatus) {
        this.growStatus = growStatus;
    }

    public String getLastOperation() {
        return lastOperation;
    }

    public void setLastOperation(String lastOperation) {
        this.lastOperation = lastOperation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Tillage other = (Tillage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPlantId() == null ? other.getPlantId() == null : this.getPlantId().equals(other.getPlantId()))
                && (this.getCurrentImg() == null ? other.getCurrentImg() == null : this.getCurrentImg().equals(other.getCurrentImg()))
                && (this.getCurrentVideo() == null ? other.getCurrentVideo() == null : this.getCurrentVideo().equals(other.getCurrentVideo()))
                && (this.getGrowStatus() == null ? other.getGrowStatus() == null : this.getGrowStatus().equals(other.getGrowStatus()))
                && (this.getLastOperation() == null ? other.getLastOperation() == null : this.getLastOperation().equals(other.getLastOperation()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getSign() == null ? other.getSign() == null : this.getSign().equals(other.getSign()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlantId() == null) ? 0 : getPlantId().hashCode());
        result = prime * result + ((getCurrentImg() == null) ? 0 : getCurrentImg().hashCode());
        result = prime * result + ((getCurrentVideo() == null) ? 0 : getCurrentVideo().hashCode());
        result = prime * result + ((getGrowStatus() == null) ? 0 : getGrowStatus().hashCode());
        result = prime * result + ((getLastOperation() == null) ? 0 : getLastOperation().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getSign() == null) ? 0 : getSign().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", plantId=").append(plantId);
        sb.append(", currentImg=").append(currentImg);
        sb.append(", currentVideo=").append(currentVideo);
        sb.append(", growStatus=").append(growStatus);
        sb.append(", lastOperation=").append(lastOperation);
        sb.append(", createTime=").append(createTime);
        sb.append(", sign=").append(sign);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}