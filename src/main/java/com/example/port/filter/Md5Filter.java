package com.example.port.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.port.interceptor.BodyReaderHttpServletRequestWrapper;
import com.example.port.utils.MD5Utils;
import com.example.port.utils.SaltEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lilin
 * @date 2019-02-01
 */
public class Md5Filter implements Filter {

    Logger logger = LoggerFactory.getLogger(Md5Filter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("过滤器初始化！");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        // 使用自定义的请求类，将请求流读取后复制一份到对象的body中，解决request请求流只能读取一次的问题
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        if(RequestMethod.GET.name().equals(request.getMethod())){
            filterChain.doFilter(requestWrapper,servletResponse);
            return;
        }
        String token = ((BodyReaderHttpServletRequestWrapper) requestWrapper).getHeader("token");
        String body = ((BodyReaderHttpServletRequestWrapper) requestWrapper).getBodyString();
        String md5Encode = MD5Utils.MD5Encode(body, SaltEnum.MD5_SALT.getCode());
        if(token.equals(md5Encode)){
            filterChain.doFilter(requestWrapper,servletResponse);
        }else {
            logger.info("md5校验不过，服务端："+md5Encode+",客户端："+token);
            PrintWriter writer = servletResponse.getWriter();
            String responseStr = "{\"code\":\"500\",\"msg\":\"md5 wrong\"}";
            Object parse = JSONObject.parse(responseStr);
            writer.print(parse);
            return;
        }

    }
}
