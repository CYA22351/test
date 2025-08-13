package com.cya.spingiocdemo.v1;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/13 21:11
 * @description：
 * @modified By：
 * @version:
 */
public class Bottom {
    private Tire tire;

    public Bottom(Integer size) {
        this.tire=new Tire(size);
        System.out.println("bottom init ...");
    }
}