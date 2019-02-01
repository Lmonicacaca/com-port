package com.example.port.filter;

import com.example.port.interceptor.BodyReaderHttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author lilin
 * @date 2019-01-31
 */
public class MyFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("过滤器初始化！");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        // 使用自定义的请求类，将请求流读取后复制一份到对象的body中，解决request请求流只能读取一次的问题
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        filterChain.doFilter(requestWrapper,servletResponse);
    }
}
