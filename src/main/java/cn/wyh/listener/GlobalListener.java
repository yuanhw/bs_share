package cn.wyh.listener;

import cn.wyh.common.Global;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by WYH on 2018/1/17.
 */
public class GlobalListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Global.baseUri = servletContextEvent.getServletContext().getRealPath("/");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
