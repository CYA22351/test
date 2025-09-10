package com.cya.springaopdemo.proxy;

import java.lang.reflect.Proxy;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/10 21:13
 * @description：
 * @modified By：
 * @version:
 */
public class Main {
    public static void main(String[] args) {
//        HouseSubject target=new RealHouseSubject();
//        HouseSubject houseSubject=new HouseProxy(target);
////        houseSubject.saleHouse();
//        houseSubject.rentHouse();
        HouseSubject target=new RealHouseSubject();
        HouseSubject proxy= (HouseSubject) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[]{HouseSubject.class},
                new JDKInvocationHandler(target)
        );
        proxy.rentHouse();
    }
}