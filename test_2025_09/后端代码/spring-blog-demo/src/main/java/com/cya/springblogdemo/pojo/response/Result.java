package com.cya.springblogdemo.pojo.response;

import com.cya.springblogdemo.enums.ResultCodeEnum;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 17:21
 * @description：
 * @modified By：
 * @version:
 */
//统一结果格式
@Data
public class Result {
    private ResultCodeEnum code;//业务状态码
//    返回的错误信息
    private String errMsg;
    private Object data;

    public static Result success(Object data){
        Result result=new Result();
        result.setCode(ResultCodeEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result fail(String errMsg){
        Result result=new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        return result;

    }
    public static Result fail(Object data, String errMsg){
        Result result=new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setData(data);
        result.setErrMsg(errMsg);
        return result;

    }
}