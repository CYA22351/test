package com.cya.spingiocdemo.component;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/14 13:45
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class UserComponent {
    public void print(){
        System.out.println("do Component");
    }
}