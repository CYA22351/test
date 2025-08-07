package com.cya.springbootdemo2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/7 14:47
 * @description：
 * @modified By：
 * @version:
 */
@RestController
public class Hellospirng {
    @RequestMapping("/say")
public String say(){
    return "hello,springMVC";
}
}