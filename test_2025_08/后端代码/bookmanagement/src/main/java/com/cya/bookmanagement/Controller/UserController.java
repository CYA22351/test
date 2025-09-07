package com.cya.bookmanagement.Controller;

import com.cya.bookmanagement.constant.Constants;
import com.cya.bookmanagement.entity.UserInfo;
import com.cya.bookmanagement.service.UserInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/12 12:02
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@RequestMapping("/user")
@RestController//生成UserController对象并调用
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @RequestMapping("login")
    public Boolean login(String name, String password, HttpSession session){
        log.info("用户登录");
        if (!StringUtils.hasLength(name)||!StringUtils.hasLength(password)){
            return false;
        }
//        if ("admin".equals(username)&&"admin".equals(password)){
//            session.setAttribute("userName",username);
//            return true;
//        }
       UserInfo userInfo= userInfoService.queryUserInfoByNaem(name);
        if (userInfo==null){
            return null;
        }
        if (password.equals(userInfo.getPassword())) {

            session.setAttribute(Constants.SESSION_USER_KEY,userInfo);
            //密码正确
            return true;
        }
        //否则密码错误
        return false;
    }
}