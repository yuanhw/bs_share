package cn.wyh.dto;

import cn.wyh.entity.BlockDetail;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WYH on 2018/3/12.
 */
public class BlockDetailResponseDto implements Serializable {
    private List<BlockDetail> list;
    private Integer total;

    public BlockDetailResponseDto() {
    }

    public BlockDetailResponseDto(List<BlockDetail> list, Integer total) {
        this.list = list;
        this.total = total;
    }

    public List<BlockDetail> getList() {
        return list;
    }

    public void setList(List<BlockDetail> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
