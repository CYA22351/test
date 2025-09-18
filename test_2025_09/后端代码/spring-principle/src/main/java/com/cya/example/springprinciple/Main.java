package com.cya.example.springprinciple;

import com.cya.example.springprinciple.model.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/18 17:12
 * @description：
 * @modified By：
 * @version:
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz=Class.forName("com.cya.example.springprinciple.model.Person");
        Object object = clazz.getDeclaredConstructor().newInstance();


        System.out.println((Person)object);
        Class<?> clazz2=Class.forName("com.cya.example.springprinciple.model.Person");

        Constructor<?> declaredConstructor = clazz2.getDeclaredConstructor(String.class);
        Object o=declaredConstructor.newInstance("zhangsan");

        System.out.println((Person)o);
    }
}