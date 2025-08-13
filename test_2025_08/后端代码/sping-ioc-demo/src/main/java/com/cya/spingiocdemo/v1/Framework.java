package com.cya.spingiocdemo.v1;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/13 21:11
 * @description：
 * @modified By：
 * @version:
 */
public class Framework {
    private Bottom bottom;

    public Framework(Integer size) {
        this.bottom=new Bottom(size);
        System.out.println("framework init ...");
    }
}