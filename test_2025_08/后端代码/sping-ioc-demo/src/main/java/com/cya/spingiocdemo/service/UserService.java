package com.cya.spingiocdemo.service;

import com.cya.spingiocdemo.model.Student;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/14 13:45
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class UserService {
    @Qualifier("s2")
   @Autowired
//    @Resource(name = "s2")
    public Student s1;

    public void print(){
        System.out.println("do service");
        System.out.println(s1);
    }
}