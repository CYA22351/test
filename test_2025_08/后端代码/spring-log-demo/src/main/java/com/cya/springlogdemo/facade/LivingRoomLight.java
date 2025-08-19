package com.cya.springlogdemo.facade;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/19 10:11
 * @description：
 * @modified By：
 * @version:
 */
public class LivingRoomLight implements Light{
    @Override
    public void on() {
        System.out.println("打开客厅灯");
    }

    @Override
    public void off() {
        System.out.println("关闭客厅灯");
    }
}