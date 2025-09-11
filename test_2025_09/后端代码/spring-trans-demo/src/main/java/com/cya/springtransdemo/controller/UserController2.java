package com.cya.springtransdemo.controller;

import com.cya.springtransdemo.mapper.UserInfoMapper;
import com.cya.springtransdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;

import java.io.IOException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/11 16:07
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/user2")
@Slf4j
public class UserController2 {
    @Autowired
    private UserService userService;
    @Autowired
    private View error;

    @RequestMapping("/registry")
        @Transactional//当发生异常事务回滚，正常运行，正常提交，但如果异常进行捕获，事务正常提交，如果异常重新抛出，事务还是会进行回滚
    public String registry(String name, String password) {


        Integer result=userService.registryUser(name, password);
        log.info("用户注册成功，影响行数：{}",result);

        try {
            int a=10/0;
        }catch (Exception e){
            log.error("程序发生异常");
//            throw new RuntimeException(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return "注册成功";
    }

    //设置触发回滚的异常类型
    @RequestMapping("/r2")
    @Transactional(rollbackFor = {Exception.class, Error.class})//当发生异常事务回滚，正常运行，正常提交，但如果异常进行捕获，事务正常提交，如果异常重新抛出，事务还是会进行回滚
    public String r2(String name, String password) throws IOException {


        Integer result=userService.registryUser(name, password);
        log.info("用户注册成功，影响行数：{}",result);
if (true){
    throw new IOException();//不会回滚
}

        return "注册成功";
    }


//事务隔离机制isolation = Isolation.READ_COMMITTED
    @RequestMapping("/r3")
    @Transactional(rollbackFor = {Exception.class, Error.class} ,isolation = Isolation.READ_COMMITTED)//当发生异常事务回滚，正常运行，正常提交，但如果异常进行捕获，事务正常提交，如果异常重新抛出，事务还是会进行回滚
    public String r3(String name, String password) throws IOException {


        Integer result=userService.registryUser(name, password);
        log.info("用户注册成功，影响行数：{}",result);
        if (true){
            throw new IOException();//不会回滚
        }

        return "注册成功";
    }

    @RequestMapping("/r4")
    @Transactional(propagation = Propagation.REQUIRES_NEW)//当发生异常事务回滚，正常运行，正常提交，但如果异常进行捕获，事务正常提交，如果异常重新抛出，事务还是会进行回滚
    public String r4(String name, String password) throws IOException {


        Integer result=userService.registryUser(name, password);
        log.info("用户注册成功，影响行数：{}",result);
        if (true){
            throw new IOException();//不会回滚
        }

        return "注册成功";
    }
}