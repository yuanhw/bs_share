package cn.wyh.common.response;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by WYH on 2018/2/7.
 */
public class Response implements Serializable {
    private Integer status;
    private String msg;
    private Object data;

    public Response(Object data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
