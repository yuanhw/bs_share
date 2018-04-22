package cn.wyh.utils;

import com.tencent.xinge.Message;
import com.tencent.xinge.XingeApp;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by WYH on 2018/4/22.
 */
public class MyXingeApp extends XingeApp {

    private long m_accessId;

    public MyXingeApp(long accessId, String secretKey) {
        super(accessId, secretKey);
        this.m_accessId = accessId;
    }

    @Override
    public JSONObject pushSingleDevice(String deviceToken, Message message) {
        if (!this.ValidateMessageType(message)) {
            return new JSONObject("{\'ret_code\':-1,\'err_msg\':\'message type error!\'}");
        } else if (!message.isValid()) {
            return new JSONObject("{\'ret_code\':-1,\'err_msg\':\'message invalid!\'}");
        } else {
            HashMap params = new HashMap();
            params.put("access_id", Long.valueOf(m_accessId));
            params.put("expire_time", Integer.valueOf(message.getExpireTime()));
            params.put("send_time", message.getSendTime());
            params.put("multi_pkg", Integer.valueOf(message.getMultiPkg()));
            params.put("device_token", deviceToken);
            params.put("message_type", Integer.valueOf(message.getType()));
            params.put("message", message.toJson());
            params.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000L));
            return this.callRestful("http://openapi.xg.qcloud.com/v2/push/single_device", params);
        }
    }

    public static JSONObject pushTokenAndroid(long accessId, String secretKey, String title, String content, String token) {
        Message message = new Message();
        message.setType(1);
        message.setTitle(title);
        message.setContent(content);
        MyXingeApp xinge = new MyXingeApp(accessId, secretKey);
        JSONObject ret = xinge.pushSingleDevice(token, message);
        return ret;
    }
}
