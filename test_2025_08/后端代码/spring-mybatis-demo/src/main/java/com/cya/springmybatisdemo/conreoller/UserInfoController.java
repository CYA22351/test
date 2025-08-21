package com.cya.springmybatisdemo.conreoller;

import com.cya.springmybatisdemo.model.UserInfo;
import com.cya.springmybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/21 11:24
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("/user")
@RestController
public class UserInfoController {

    @Autowired
    private UserService service;
    @RequestMapping("/getAllUser")
    public List<UserInfo> getAllUser(){
        return service.getAllUser();
    }
}