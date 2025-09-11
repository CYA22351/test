package com.cya.springaopdemo.proxy;

import net.sf.cglib.proxy.Enhancer;

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
//    动态代理    jdk
//        HouseSubject proxy= (HouseSubject) Proxy.newProxyInstance(
//                target.getClass().getClassLoader(),
//                new Class[]{HouseSubject.class},
//                new JDKInvocationHandler(target)
//        );
//        proxy.rentHouse();

//动态代理CGLib  --add-opens java.base/java.lang=ALL-UNNAMED
        RealHouseSubject proxy= (RealHouseSubject) Enhancer.create(target.getClass(),new CGLIBInterceptor(target));
        proxy.rentHouse();
    }
}