package com.cya.springaopdemo.proxy;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/10 21:11
 * @description：
 * @modified By：
 * @version:
 */
public class HouseProxy implements HouseSubject{
    private HouseSubject realHouseSubject;

    @Override
    public void saleHouse() {
        System.out.println("我是中介，我帮房东进行代理卖房");
        realHouseSubject.saleHouse();
        System.out.println("我是中介，我帮房东结束代理卖房");
    }

    @Override
    public void rentHouse() {
        System.out.println("我是中介，我帮房东进行代理租房");
        realHouseSubject.rentHouse();
        System.out.println("我是中介，我帮房东结束代理租房");

    }

    public HouseProxy(HouseSubject realHouseSubject) {
        this.realHouseSubject = realHouseSubject;
    }
}