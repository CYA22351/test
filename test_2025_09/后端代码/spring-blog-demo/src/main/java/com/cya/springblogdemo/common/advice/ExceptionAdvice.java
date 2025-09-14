package com.cya.springblogdemo.common.advice;

import com.cya.springblogdemo.common.exception.BlogException;
import com.cya.springblogdemo.pojo.response.Result;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

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
//统一异常返回
public class ExceptionAdvice {

    @ExceptionHandler
    public Result exceptionHandler(Exception exception){
        log.info("发生异常，e:{}",exception);
        return Result.fail(exception.getMessage());
    }

    @ExceptionHandler
    public Result BlogException(BlogException exception){
        log.info("发生异常，e:{}",exception);
        return Result.fail(exception.getErrMsg());
    }

    //详情id为空异常
    @ExceptionHandler
    public Result BlogException(HandlerMethodValidationException exception){
        log.info("发生异常，e:{}",exception.getMessage());
        return Result.fail("参数校验失败");
    }

    @ExceptionHandler
    public Result BlogException(MethodArgumentNotValidException exception){
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        log.info("发生异常，e:{}",exception.getMessage());
        return Result.fail(message);
    }




}