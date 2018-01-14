package cn.wyh;

import cn.wyh.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by WYH on 2017/12/13.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService farm = context.getBean(UserService.class);
        farm.deleteUser(42);
    }
}
