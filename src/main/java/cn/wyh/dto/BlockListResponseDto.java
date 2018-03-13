package cn.wyh.dto;

import cn.wyh.entity.BlockRule;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WYH on 2018/3/8.
 */
public class BlockListResponseDto implements Serializable {
    private List<BlockRule> list;
    private Integer total;

    public BlockListResponseDto() {
    }

    public BlockListResponseDto(List<BlockRule> list, Integer total) {
        this.list = list;
        this.total = total;
    }

    public List<BlockRule> getList() {
        return list;
    }

    public void setList(List<BlockRule> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
