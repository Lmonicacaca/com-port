package com.example.port.interceptor;

import com.example.port.utils.MD5Utils;
import com.example.port.utils.SaltEnum;
import com.example.port.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lilin
 * @date 2019-01-31
 */
@Component
public class Md5Interceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(Md5Interceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            // 过滤get请求
            if(request.getMethod().equals(RequestMethod.GET.name())){
                return true;
            }
            String md5 = request.getHeader("token");
            String body = IOUtils.toString(request.getInputStream(),"UTF-8");
            String md5Encode = MD5Utils.MD5Encode(body, SaltEnum.MD5_SALT.getCode());
            if(md5.equals(md5Encode)){
                return true;
            }else {
                logger.info("服务端md5："+md5Encode+",参数md5:"+md5+",md5不通过！！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
