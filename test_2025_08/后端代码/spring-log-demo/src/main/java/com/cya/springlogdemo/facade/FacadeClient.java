package com.cya.springlogdemo.facade;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/19 10:14
 * @description：
 * @modified By：
 * @version:
 */
public class FacadeClient implements Light{
    private LivingRoomLight livingRoomLight=new LivingRoomLight();
    private BedRoomLight bedRoomLight=new BedRoomLight();
    private HallLight hallLight=new HallLight();

    @Override
    public void on() {
        livingRoomLight.on();
        bedRoomLight.on();
        hallLight.on();
    }

    @Override
    public void off() {
livingRoomLight.off();
bedRoomLight.off();
hallLight.off();
    }
}