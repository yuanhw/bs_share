package cn.wyh.utils;

import org.json.JSONObject;

/**
 * Created by WYH on 2018/4/21.
 */
public class InfoPushUtil {

    public static void sendMessage() {
        JSONObject obj = MyXingeApp.pushTokenAndroid(2100282588,
                "a22c12e07bc64142c0394a93aafad5ef",
                "test",
                "测试333",
                "362acc38066bfe2db9526eaf03b50f1c9a5b9750");
        System.out.println(obj);
    }
}
