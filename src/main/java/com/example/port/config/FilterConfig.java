package com.example.port.config;

import com.example.port.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author lilin
 * @date 2019-01-31
 * 过滤器配置类
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean getChannelFilterRegistrationBean(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new MyFilter());
        bean.addUrlPatterns("/*");
        bean.setName("MyFilter");
        bean.setOrder(10);
        bean.setEnabled(true);
        return bean;
    }
}
