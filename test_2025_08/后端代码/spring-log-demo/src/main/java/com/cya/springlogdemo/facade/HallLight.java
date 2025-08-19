package com.cya.springlogdemo.facade;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/19 10:13
 * @description：
 * @modified By：
 * @version:
 */
public class HallLight implements Light{
    @Override
    public void on() {
        System.out.println("打开走廊灯");
    }

    @Override
    public void off() {
        System.out.println("关闭走廊灯");
    }
}