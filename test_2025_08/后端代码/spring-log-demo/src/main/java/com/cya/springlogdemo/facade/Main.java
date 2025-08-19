package com.cya.springlogdemo.facade;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/19 10:31
 * @description：
 * @modified By：
 * @version:
 */
public class Main {
    public static void main(String[] args) {
        FacadeClient facadeClient=new FacadeClient();
        facadeClient.on();
        facadeClient.off();
    }
}
