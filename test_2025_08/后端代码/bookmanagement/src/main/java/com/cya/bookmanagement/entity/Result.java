package com.cya.bookmanagement.entity;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/5 10:33
 * @description：
 * @modified By：
 * @version:
 */

import com.cya.bookmanagement.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class Result<T> {
    private ResultCodeEnum code;
    private String errMsg;
    private T data;
    public static <T>Result success(T data){
        Result result=new Result();
        result.setCode(ResultCodeEnum.SUCCESS);
        result.setErrMsg("");
        result.setData(data);
        return result;
    }
    public static <T>Result fail(String errMsg){
        Result result=new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        result.setData(null);
        return result;
    }
    public static <T>Result fail(String errMsg,T data){
        Result result=new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        result.setData(data);
        return result;
    }

    public static <T>Result unlogin(){
        Result result=new Result();
        result.setCode(ResultCodeEnum.UNLOGIN);
        result.setErrMsg("用户未登录");
        result.setData(null);
        return result;
    }
}