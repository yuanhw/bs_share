package cn.wyh;

import cn.wyh.utils.MyXingeApp;
import org.json.JSONObject;

/**
 * Created by WYH on 2017/12/13.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException, IllegalAccessException {
        long accessId = 2100282588;
        final String secretKey = "a22c12e07bc64142c0394a93aafad5ef";
        JSONObject obj = MyXingeApp.pushTokenAndroid(
                accessId,
                secretKey,
                "ceshi",
                "333",
                "362acc38066bfe2db9526eaf03b50f1c9a5b9750");
        System.out.println(obj);
    }
}
