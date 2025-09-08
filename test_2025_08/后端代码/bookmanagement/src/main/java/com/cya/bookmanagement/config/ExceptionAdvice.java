package com.cya.bookmanagement.config;

import com.cya.bookmanagement.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/8 18:00
 * @description：
 * @modified By：
 * @version:
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

//    @ExceptionHandler
//    public Result handler(Exception e){
//        log.error("发生异常，e:"+e);
//         return Result.fail("内部信息错误");
//    }
//
//    @ExceptionHandler
//    public Result handler(NullPointerException e){
//        log.error("发生异常，e:"+e);
//        return Result.fail("空指针异常，请联系管理员");
//    }
//    @ExceptionHandler
//    public Result handler(IndexOutOfBoundsException e){
//        log.error("发生异常，e:"+e);
//        return Result.fail("数组越界异常，请联系管理员");
//    }
@ExceptionHandler(Exception.class)
public Result handler1(Exception e){
    log.error("发生异常，e:"+e);
    return Result.fail("内部信息错误");
}

    @ExceptionHandler(NullPointerException.class)
    public Result handler2(Exception e){
        log.error("发生异常，e:"+e);
        return Result.fail("空指针异常，请联系管理员");
    }
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Result handler3(Exception e){
        log.error("发生异常，e:"+e);
        return Result.fail("数组越界异常，请联系管理员");
    }


}