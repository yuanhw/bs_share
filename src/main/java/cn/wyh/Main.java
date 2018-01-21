package cn.wyh;

import cn.wyh.service.ProvinceService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by WYH on 2017/12/13.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        ProvinceService service = context.getBean(ProvinceService.class);

        System.out.println(JSONArray.toJSONString(service.loadListProvince()));

        /*
        Province province = new Province();
        province.setCode(1);
        province.setValue("浙江省");
        List<City> list = new ArrayList<City>();
        list.add(new City(1, 1, "杭州市", "杭州市"));
        list.add(new City(2, 1, "嘉兴市", "嘉兴市"));
        province.setChildren(list);
        System.out.println(JSON.toJSONString(province));
        */
    }
}
