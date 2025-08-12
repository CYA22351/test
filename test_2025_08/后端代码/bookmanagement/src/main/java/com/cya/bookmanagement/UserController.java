package com.cya.bookmanagement;

import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping("login")
    public Boolean login(String username, String password, HttpSession session){
        if (!StringUtils.hasLength(username)||!StringUtils.hasLength(password)){
            return false;
        }
        if ("admin".equals(username)&&"admin".equals(password)){
            session.setAttribute("userName",username);
            return true;
        }
        return false;
    }
}