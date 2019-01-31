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
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        filterChain.doFilter(requestWrapper,servletResponse);
    }
}
