package com.cya.springlogdemo.facade;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/19 10:12
 * @description：
 * @modified By：
 * @version:
 */
public class BedRoomLight implements Light{
    @Override
    public void on() {
        System.out.println("打开卧室灯");
    }

    @Override
    public void off() {
        System.out.println("关闭卧室灯");
    }
}