package com.cya.forumproject;

import com.cya.forumproject.dao.UserMapper;
import com.cya.forumproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class ForumProjectApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private DataSource dataSource;

    @Test
    void testconnect() throws SQLException {
        System.out.println("datasource ="+dataSource.getClass());
        Connection connection=dataSource.getConnection();
        System.out.println("connection ="+connection);
    }

    @Test
    void contextLoads() {
        System.out.println("Test，基于spring的论坛系统");
    }

    @Test
    void testMybatis(){
        User user = userMapper.selectByPrimaryKey(1l);
        System.out.println(user);
        System.out.println(user.getUsername());
    }

}
