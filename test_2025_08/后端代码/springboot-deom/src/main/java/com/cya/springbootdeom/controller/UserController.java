package com.cya.springbootdeom.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/15 21:58
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("Bookuser")
@RestController
public class UserController {
    @RequestMapping("login")
    public boolean login(String username, String password, HttpSession session){
        if (!StringUtils.hasLength(username)||!StringUtils.hasLength(password)){
            return false;
        }
        if ("admin".equals(username)&&"admin".equals(password)) {
            return true;
        }
        return false;
    }
}