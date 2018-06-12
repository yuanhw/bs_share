package cn.wyh.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/4/17.
 */
public class PlantStatusWebDto implements Serializable {
    private Integer plantId;
    private Integer blockId;
    private String greenTitle;
    private String greenImg;
    private String greenStatus;
    private String greenOperate;
    private String lastOperator;
    private Integer currentImgId;
    private String currentVideo;
    private Integer tillageId;
    private Date lastTime;
    private int status;

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

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

    public String getGreenStatus() {
        return greenStatus;
    }

    public void setGreenStatus(String greenStatus) {
        this.greenStatus = greenStatus;
    }

    public String getGreenOperate() {
        return greenOperate;
    }

    public void setGreenOperate(String greenOperate) {
        this.greenOperate = greenOperate;
    }


    public Integer getCurrentImgId() {
        return currentImgId;
    }

    public void setCurrentImgId(Integer currentImgId) {
        this.currentImgId = currentImgId;
    }

    public String getCurrentVideo() {
        return currentVideo;
    }

    public void setCurrentVideo(String currentVideo) {
        this.currentVideo = currentVideo;
    }

    public Integer getTillageId() {
        return tillageId;
    }

    public void setTillageId(Integer tillageId) {
        this.tillageId = tillageId;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
