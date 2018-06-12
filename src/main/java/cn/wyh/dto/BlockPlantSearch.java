package cn.wyh.dto;

import java.io.Serializable;

/**
 * Created by WYH on 2018/4/17.
 */
public class BlockPlantSearch implements Serializable {
    private Integer plantId;
    private Integer blockId;
    private Integer status;
    private Integer farmManagerId;
    private Integer currentPage;
    private int startIndex;
    private int pageNum;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFarmManagerId() {
        return farmManagerId;
    }

    public void setFarmManagerId(Integer farmManagerId) {
        this.farmManagerId = farmManagerId;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
