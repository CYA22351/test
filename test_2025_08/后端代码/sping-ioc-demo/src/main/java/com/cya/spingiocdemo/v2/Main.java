package com.cya.spingiocdemo.v2;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/13 21:10
 * @description：
 * @modified By：
 * @version:
 */
public class Main {
    public static void main(String[] args) {
        Framework framework=new Framework(new Bottom(new Tire(21,"红色")));
        Car car=new Car(framework);
        car.run();
    }
}