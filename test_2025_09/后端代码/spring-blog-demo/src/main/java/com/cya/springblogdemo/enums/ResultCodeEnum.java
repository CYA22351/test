package com.cya.springblogdemo.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 17:23
 * @description：
 * @modified By：
 * @version:
 */
public enum ResultCodeEnum {
    SUCCESS(200),
    FAIL(-1);
    @Setter @Getter
    private int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }
}