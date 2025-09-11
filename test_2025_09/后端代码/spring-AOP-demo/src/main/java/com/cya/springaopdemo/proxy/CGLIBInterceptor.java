package com.cya.springaopdemo.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/11 12:50
 * @description：
 * @modified By：
 * @version:
 */
public class CGLIBInterceptor implements MethodInterceptor {

    private  Object traget;

    public CGLIBInterceptor(Object traget) {
        this.traget = traget;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我是中介，开始代理");
        Object invoke = methodProxy.invoke(traget, objects);
        System.out.println("我是中介，结束代理");
        return invoke;
    }
}