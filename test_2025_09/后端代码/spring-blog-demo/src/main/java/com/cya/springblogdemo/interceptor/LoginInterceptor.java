package com.cya.springblogdemo.interceptor;

import com.cya.springblogdemo.constant.Constants;
import com.cya.springblogdemo.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/14 11:27
 * @description：
 * @modified By：
 * @version:
 */
//定义拦截器，强制登陆
    @Component
    @Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userToken = request.getHeader(Constants.USER_TOKEN_HEADER_KEY);
        log.info("从header中获取token:"+userToken);
        if (userToken==null){
            //用户未登录
            response.setStatus(401);
            return false;
        }
        //检验token是否合法
        Claims claims =(Claims) JwtUtils.parssToken(userToken);
        if (claims==null){
            response.setStatus(401);
            return false;

        }


        return true;
    }
}