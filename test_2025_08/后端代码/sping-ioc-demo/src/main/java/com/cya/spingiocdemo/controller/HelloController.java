package com.cya.spingiocdemo.controller;

import com.cya.spingiocdemo.config.UserConfig;
import com.cya.spingiocdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/14 10:29
 * @description：
 * @modified By：
 * @version:
 */
@Controller//将对象交给spring
public class HelloController {
    //属性注入
    @Autowired
    private UserService userService;
    @Autowired
    private UserConfig userConfig;
//构造方法注入

//    public HelloController(UserService userService) {
//        this.userService = userService;
//    }
//@Autowired
//    public HelloController(UserService userService, UserConfig userConfig) {
//        this.userService = userService;
//        this.userConfig = userConfig;
//    }
//        public HelloController() {
//    }

    //setter方法注入
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//@Autowired
//    public void setUserConfig(UserConfig userConfig) {
//        this.userConfig = userConfig;
//    }

    public void print(){

        System.out.println("do Controller");
        System.out.println(userService);
        userService.print();
        userConfig.print();
    }
}