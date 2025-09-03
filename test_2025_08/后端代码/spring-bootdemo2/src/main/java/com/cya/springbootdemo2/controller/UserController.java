package com.cya.springbootdemo2.controller;

import com.cya.springbootdemo2.modle.MessageInfo;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/7 15:15
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("user")
@RestController
public class UserController {
    //既支持get，也支持post
    @RequestMapping("m1")
    public String m1(){
        return "m1";
    }
    //只支持Get
    @RequestMapping(value = "m2",method = RequestMethod.GET)
    public String m2(){
        return "m2";
    }
    //只支持Get
    @GetMapping("m4")
    public String m4(){
        return "m4";
    }
    //只支持Post
    @RequestMapping(value = "m3",method = RequestMethod.POST)
    //只支持Post
    @PatchMapping("m5")
    public String m5(){
        return "m5";
    }
    public void testLombok(){
        MessageInfo messageInfo=new MessageInfo();
        messageInfo.setMessage(" ");
    }
}