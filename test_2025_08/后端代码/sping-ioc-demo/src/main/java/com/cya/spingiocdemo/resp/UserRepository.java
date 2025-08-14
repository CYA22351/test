package com.cya.spingiocdemo.resp;

import org.springframework.stereotype.Repository;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/14 13:48
 * @description：
 * @modified By：
 * @version:
 */
@Repository
public class UserRepository {
    public void print(){
        System.out.println("do service");
    }
}