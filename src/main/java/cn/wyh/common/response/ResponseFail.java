package cn.wyh.common.response;

import cn.wyh.common.CodeError;
import cn.wyh.common.CodeMsg;

import java.io.Serializable;

/**
 * Created by WYH on 2018/2/7.
 */
public class ResponseFail extends Response implements Serializable {
    public ResponseFail(Object data) {
        super(data);
        super.setStatus(CodeError.RESPONSE_FAIL);
        super.setMsg(CodeMsg.RESPONSE_FAIL_MSG);
    }
}
