package com.cya.bookmanagement.interceptor;

import com.cya.bookmanagement.constant.Constants;
import com.cya.bookmanagement.entity.Result;
import com.cya.bookmanagement.entity.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/7 16:42
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j//日志
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle执行前");
//true不拦截，false拦截
        HttpSession session = request.getSession(false);
      if(!checkUser(session)){
          response.setContentType("text/html,charset=utf-8");//application/json
          response.setStatus(401);
          String msg="用户未登录";
          response.getOutputStream().write(msg.getBytes("utf-8"));
          return false;
      }
      return true;

    }
    public boolean checkUser(HttpSession session){
        if (session==null||session.getAttribute(Constants.SESSION_USER_KEY)==null){
//            response.setStatus(401);//未登录
//            return false;
            log.warn("用户未登录");

            return false;

        }

        UserInfo userInfo =(UserInfo) session.getAttribute(Constants.SESSION_USER_KEY);
        if (userInfo==null||userInfo.getId()<=0){
//            response.setStatus(401);//未登录
            log.warn("用户未登录");
            return false;
        }
        log.info("用户已登录");
        return true;//true不拦截，false拦截
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
      log.info("方法执行后");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//     log.info("afterCompletion...");
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
}