package com.cya.bookmanagement.enums;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/5 10:37
 * @description：
 * @modified By：
 * @version:
 */
public enum ResultCodeEnum {
    UNLOGIN(-1),
    SUCCESS(200),
    FAIL(-20);
    private int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }
}