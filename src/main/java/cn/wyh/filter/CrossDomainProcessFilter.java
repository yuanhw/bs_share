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

    /**
     * vue跨域请求处理
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("log - cross domain process ------------------------- uri = " + servletRequest.getRemoteAddr());
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST,GET");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {}
}
