package com.cya.example.springprinciple.component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/17 17:48
 * @description：
 * @modified By：
 * @version:
 */
//bean生命周期
@Component
public class BeanLifeComponent implements BeanNameAware {

    //实例化
    private DogComponent dogComponent;

    public BeanLifeComponent() {
        System.out.println("执行构造方法");
    }


    //属性赋值
    @Autowired
    public void setDogComponent(DogComponent dogComponent) {
        this.dogComponent = dogComponent;
        System.out.println("执行属性赋值");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("执行BeanNameAware,name:"+name);
    }

    @PostConstruct//初始化方法
    public  void init(){
        System.out.println("执行初始化方法");
    }

    //使用bean
    public void use(){
        System.out.println("使用bean，执行use方法");
    }

    @PreDestroy//销毁bean
    public void destory(){
        System.out.println("销毁bean");
    }

}