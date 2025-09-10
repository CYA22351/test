package com.cya.springaopdemo.proxy;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/10 21:11
 * @description：
 * @modified By：
 * @version:
 */
public class RealHouseSubject implements HouseSubject{
    @Override
    public void saleHouse() {
        System.out.println("我是房东，我卖房");
    }

    @Override
    public void rentHouse() {
        System.out.println("我是房东，我出租房子");
    }
}