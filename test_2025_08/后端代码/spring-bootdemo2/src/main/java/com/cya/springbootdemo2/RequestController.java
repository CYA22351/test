package com.cya.springbootdemo2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/7 22:20
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("request")
@RestController
public class RequestController {
    @RequestMapping("r1")
    public String r1( String keyword){
        return "接收参数:"+keyword;
    }
    @RequestMapping("r2")
    public String r2( String username,String password){
        return "接收参数： userName:"+username+", password: "+password;
    }
}