package com.cya.springblogdemo.common.advice;

import com.cya.springblogdemo.common.exception.BlogException;
import com.cya.springblogdemo.pojo.response.Result;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 17:51
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public Result exceptionHandler(Exception exception){
        log.info("发生异常，e:",exception);
        return Result.fail(exception.getMessage());
    }

    @ExceptionHandler
    public Result BlogException(BlogException exception){
        log.info("发生异常，e:",exception);
        return Result.fail(exception.getMessage());
    }


}