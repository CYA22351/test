package com.cya.spingiocdemo.v1;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/13 21:49
 * @description：
 * @modified By：
 * @version:
 */
public class Tire {
    public int size;
    public Tire(Integer size) {
        this.size=size;
        System.out.println("tire init,size:"+size);
    }
}