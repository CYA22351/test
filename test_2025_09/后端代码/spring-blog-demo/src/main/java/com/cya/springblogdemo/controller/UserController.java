package com.cya.springblogdemo.controller;

import com.cya.springblogdemo.pojo.request.UserLoginRequest;
import com.cya.springblogdemo.pojo.response.UserInfoResponse;
import com.cya.springblogdemo.pojo.response.UserLoginResponse;
import com.cya.springblogdemo.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 18:00
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userService;

    @RequestMapping("/login")
//    @RequestBody参数类型为json
    public UserLoginResponse login(@RequestBody @Validated UserLoginRequest userLoginRequest){
        log.info("用户登录，用户名:{}",userLoginRequest.getUserName());
        return userService.checkPassword(userLoginRequest);
    }

    @RequestMapping("getUserInfo")
    public UserInfoResponse getUserInfo(@NotNull(message = "userId不能为空") Integer userId){
        log.info("获取用户Id:"+userId);
        return userService.getUserInfo(userId);
    }

    @RequestMapping("getAuthorInfo")
    public UserInfoResponse getAuthorInfo(@NotNull(message = "blogId不能为空") Integer blogId){
log.info("获取作者id："+blogId);
return userService.getAuthorInfo(blogId);


    }
}