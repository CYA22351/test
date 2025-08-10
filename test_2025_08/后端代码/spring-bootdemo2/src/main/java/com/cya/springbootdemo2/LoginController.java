package com.cya.springbootdemo2;

import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/10 15:37
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("user")
@RestController
public class LoginController {
    @RequestMapping("login")
    public boolean login(String userName, String password ,HttpSession session){
        if (!StringUtils.hasLength(userName)||!StringUtils.hasLength(password)){
            return false;
        }
        if ("admin".equals(userName)&&"123456".equals(password)){
            session.setAttribute("userName",userName);
            session.setAttribute("password",password);
            return true;
        }
        return false;
    }
    @RequestMapping("getLoginUser")
    public String getLoginUser(HttpSession session){
        String userName=(String) session.getAttribute("userName");
        return userName;
    }
}