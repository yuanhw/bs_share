package cn.wyh.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/3/8.
 */
public class BlockSearch implements Serializable {
    private String batchNo;
    private int type;
    private Date validityBegin;
    private Date validityEnd;
    private int fmManagerId;
    private int farmId;
    private int currentPage;
    private int startIndex;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getValidityBegin() {
        return validityBegin;
    }

    public void setValidityBegin(Date validityBegin) {
        this.validityBegin = validityBegin;
    }

    public Date getValidityEnd() {
        return validityEnd;
    }

    public void setValidityEnd(Date validityEnd) {
        this.validityEnd = validityEnd;
    }

    public int getFmManagerId() {
        return fmManagerId;
    }

    public void setFmManagerId(int fmManagerId) {
        this.fmManagerId = fmManagerId;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
}
