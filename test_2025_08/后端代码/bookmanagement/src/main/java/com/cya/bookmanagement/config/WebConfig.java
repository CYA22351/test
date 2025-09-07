package com.cya.bookmanagement.config;

import com.cya.bookmanagement.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/7 16:46
 * @description：
 * @modified By：
 * @version:
 */
@Configuration//配置相关
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        WebMvcConfigurer.super.addInterceptors(registry);
//        LoginInterceptor loginInterceptor=new LoginInterceptor();
        registry.addInterceptor(loginInterceptor).addPathPatterns("/book/**");//设置拦截路径

    }
}