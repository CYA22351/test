package com.cya.springaopdemo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/10 21:41
 * @description：
 * @modified By：
 * @version:
 */
public class JDKInvocationHandler implements InvocationHandler {

    private Object target;

    public JDKInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是中介。我开始代理");
        Object retVal = method.invoke(target, args);
        System.out.println("我是中介，代理结束");
        return retVal;
    }
}