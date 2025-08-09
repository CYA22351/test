package com.cya.springbootdemo2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/9 22:48
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("resp")
//@RestController
@Controller
public class RespController {
    @RequestMapping("r1")
    public String returnPage(){
        //要加/,标识相对路径
        return "/index.html";
    }

    //如果类中全是返回页面的方法，在类上加@Controller
    //如果一个类既要返回数据又要返回页面。在类上使用@Controller，在返回数据的方法上加上@ResponseBody注解
    @ResponseBody
    @RequestMapping("r2")
    public String returnDate(){
        return "数据";
    }
    @ResponseBody
    @RequestMapping("r3")
    public String r3(){
        return "<h1>我是一级标题</h1>\n";
    }
    //produces = "text/plain",返回Content-Type的类型
    @ResponseBody
    @RequestMapping(value = "r4",produces = "text/plain")
    public String returnText(){
        return "<h1>我是一级标题</h1>\n";
    }
    @ResponseBody
    @RequestMapping(value = "r5")
    public userInfo returnJson(){
        userInfo userInfo1=new userInfo("张三",1,1);
        return userInfo1;
           }
    @ResponseBody
    @RequestMapping(value = "r6")
    public userInfo setStatus(HttpServletRequest request, HttpServletResponse response){
        response.setStatus(404);
        userInfo userInfo1=new userInfo("张三",1,1);
        return userInfo1;
    }
    @ResponseBody
    @RequestMapping(value = "r7")
    public String setHeader(HttpServletRequest request, HttpServletResponse response){
       response.setHeader("myHeader","myHeader");
       return "设置header成功";
    }
}