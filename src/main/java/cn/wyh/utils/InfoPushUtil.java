package cn.wyh.utils;

import org.json.JSONObject;

/**
 * Created by WYH on 2018/4/21.
 */
public class InfoPushUtil {

    private static final long accessId = 2100282588;
    private static final String secretKey = "a22c12e07bc64142c0394a93aafad5ef";

    public static void sendMessage(final Message message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject obj = MyXingeApp.pushTokenAndroid(
                        accessId,
                        secretKey,
                        message.getTitle(),
                        message.getContent(),
                        message.getToken().trim());
                System.out.println(obj);
            }
        }).start();
    }

    public static class Message {
        private String title;
        private String content;
        private String token;

        public Message(String title, String content, String token) {
            this.title = title;
            this.content = content;
            this.token = token;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
