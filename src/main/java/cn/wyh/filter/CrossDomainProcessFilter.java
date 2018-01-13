package cn.wyh.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WYH on 2017/12/13.
 */
public class CrossDomainProcessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("log - cross domain process ************************");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST,GET");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
