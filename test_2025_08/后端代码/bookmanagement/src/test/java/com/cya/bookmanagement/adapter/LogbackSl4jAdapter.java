package com.cya.bookmanagement.adapter;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/8 11:37
 * @description：
 * @modified By：
 * @version:
 */
public class LogbackSl4jAdapter implements Sl4jApi{

    private Logback logback;

    public LogbackSl4jAdapter(Logback logback) {
        this.logback = logback;
    }

    @Override
    public void log(String log) {
        logback.print("LogbackSl4jAdapter:"+log);
    }
}