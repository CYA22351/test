package com.cya.springblogdemo.common.exception;

import lombok.Data;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 17:48
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class BlogException extends RuntimeException{
    private int code;
    private String errMsg;

    public BlogException( int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public BlogException(  String errMsg) {

        this.errMsg = errMsg;
    }
}