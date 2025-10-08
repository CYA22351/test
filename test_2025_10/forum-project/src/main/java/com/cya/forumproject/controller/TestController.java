
package com.cya.forumproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/8 23:25
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/hello")
    public void hello(){
        System.out.println("hello controller");
    }
}