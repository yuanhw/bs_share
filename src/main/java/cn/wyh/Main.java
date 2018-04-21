package cn.wyh;

import cn.wyh.entity.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by WYH on 2017/12/13.
 */
public class Main {
    static {
        System.out.println("Main init ...");
    }

    static void f1() {
        System.out.println("f1");
    }

    public static void main(String[] args) throws InterruptedException, IllegalAccessException {
        User user = new User();
        user.setRegTime(new Date());
        user.setUserName("nini");
        User user2 = new User();
        user2.setRegTime(new Date());
        user2.setUserName("nini2");
        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user2);
        System.out.println(list.size());
        Iterator<User> iter = list.iterator();
        while (iter.hasNext()) {
            User obj = iter.next();
            if (obj != null && obj.getUserName().equals("nini")) {
                iter.remove();
                break;
            }
        }
        System.out.println(list.size());
    }

    public static String toString(Object obj) throws IllegalAccessException {
        Class<? extends Object> classType = obj.getClass();
        Field[] fields = classType.getDeclaredFields();
        System.out.println(fields.length);
        for (Field field : fields) {
            field.setAccessible(true);
            String key = field.getName();
            Object value = field.get(obj);
            if (value != null) {
                System.out.println("key=" + key + ", " + "value=" + value);
            }
            System.out.println("key=" + key + ", " + "value=" + value);
        }
        return null;
    }
}
