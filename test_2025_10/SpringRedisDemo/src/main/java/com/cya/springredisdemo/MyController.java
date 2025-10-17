package com.cya.springredisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/17 15:25
 * @description：
 * @modified By：
 * @version:
 */
@RestController

public class MyController {
    @Autowired
        private StringRed redisTemplate;
}