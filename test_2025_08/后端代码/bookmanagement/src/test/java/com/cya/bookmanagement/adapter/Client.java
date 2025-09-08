package com.cya.bookmanagement.adapter;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/8 11:38
 * @description：
 * @modified By：
 * @version:
 */
public class Client {
    public static void main(String[] args) {
        Sl4jApi sl4jApi=new Log4jSl4jAdapter(new Log4j());
        Sl4jApi sl4jApi1=new LogbackSl4jAdapter(new Logback());
        sl4jApi.log("我是日志");
    }
}