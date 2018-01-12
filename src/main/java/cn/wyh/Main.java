package cn.wyh;

import cn.wyh.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by WYH on 2017/12/13.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService farm = context.getBean(UserService.class);
        //farm.reg(new User("13588746259", "1", "王远航7", "男", new Date()));
        //Thread.sleep(1000);
        farm.updateLoginTime(new Date(), "13588746259");
        System.out.println(farm.findByPhone("13588746259"));
    }
}
