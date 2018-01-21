package cn.wyh.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYH on 2018/1/21.
 */
public class Province implements Serializable{
    private int code;
    private String value;
    private String label;
    private List<City> children = new ArrayList<City>();

    public Province() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        this.label = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<City> getChildren() {
        return children;
    }

    public void setChildren(List<City> children) {
        this.children = children;
    }
}
