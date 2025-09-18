package com.cya.example.springprinciple.model;

import org.springframework.context.annotation.Import;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/18 17:11
 * @description：
 * @modified By：
 * @version:
 */
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    private void getNum(Integer num){
        System.out.println(num);
    }
}