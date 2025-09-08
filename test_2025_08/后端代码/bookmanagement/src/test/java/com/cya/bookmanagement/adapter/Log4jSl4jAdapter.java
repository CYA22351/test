package com.cya.bookmanagement.adapter;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/8 11:33
 * @description：
 * @modified By：
 * @version:
 */
public class Log4jSl4jAdapter implements Sl4jApi{

    private Log4j log4j;

    public Log4jSl4jAdapter(Log4j log4j) {
        this.log4j = log4j;
    }

    @Override
    public void log(String log) {
        log4j.log("Log4jSl4jAdapter: "+log);
    }
}