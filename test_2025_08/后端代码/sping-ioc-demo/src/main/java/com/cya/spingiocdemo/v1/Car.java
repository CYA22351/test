package com.cya.spingiocdemo.v1;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/13 21:11
 * @description：
 * @modified By：
 * @version:
 */
public class Car {
    private Framework framework;

    public Car(Integer size ){
        this.framework=new Framework(size);
        System.out.println("car init....");
    }

    public void run(){
        System.out.println("car run...");
    }

}