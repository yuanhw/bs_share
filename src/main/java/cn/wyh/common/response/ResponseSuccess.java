package cn.wyh.common.response;

import cn.wyh.common.CodeError;
import cn.wyh.common.CodeMsg;

import java.io.Serializable;

/**
 * Created by WYH on 2018/2/7.
 */
public class ResponseSuccess extends Response implements Serializable {
    public ResponseSuccess(Object data) {
        super(data);
        super.setStatus(CodeError.RESPONSE_SUCCESS);
        super.setMsg(CodeMsg.RESPONSE_SUCCESS_MSG);
    }
}
