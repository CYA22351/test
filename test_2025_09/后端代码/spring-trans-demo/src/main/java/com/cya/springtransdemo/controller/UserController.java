package com.cya.springtransdemo.controller;

import com.cya.springtransdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/11 15:49
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@RequestMapping("/user")
@RestController
    public class UserController {
        @Autowired
        private UserService userService;

     @Autowired
        private DataSourceTransactionManager dataSourceTransactionManager;

     @Autowired
     private TransactionDefinition definition;

        @RequestMapping("/registry")
        public String registry(String name, String password) {
    //        1.开启事务
    //        2.数据操作
    //        3.事务提交/回滚
            //⽤⼾注册
            //1开启事务
            TransactionStatus transaction = dataSourceTransactionManager.getTransaction(definition);

    //        用户注册
            userService.registryUser(name, password);
            log.info("事务提交成功");

    //        事务的提交

    //        dataSourceTransactionManager.commit(transaction);
            dataSourceTransactionManager.rollback(transaction);
            return "注册成功";
        }
}