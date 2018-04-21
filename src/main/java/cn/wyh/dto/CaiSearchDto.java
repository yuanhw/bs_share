package cn.wyh.dto;

import java.io.Serializable;

/**
 * Created by WYH on 2018/4/21.
 */
public class CaiSearchDto implements Serializable {
    private Integer plantId;
    private Integer caiId;
    private Integer farmManagerId;
    private int status;
    private int currentPage;
    private int startIndex;

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public Integer getCaiId() {
        return caiId;
    }

    public void setCaiId(Integer caiId) {
        this.caiId = caiId;
    }

    public Integer getFarmManagerId() {
        return farmManagerId;
    }

    public void setFarmManagerId(Integer farmManagerId) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
