package com.cya.springblogdemo.config;

import com.cya.springblogdemo.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/14 11:55
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
@Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/blog/**","/user/**")
                .excludePathPatterns("/user/login");
    }
}