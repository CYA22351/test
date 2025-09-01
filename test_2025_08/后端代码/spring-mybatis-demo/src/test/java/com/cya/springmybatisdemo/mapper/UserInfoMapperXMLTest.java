package com.cya.springmybatisdemo.mapper;

import com.cya.springmybatisdemo.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserInfoMapperXMLTest {

    @Autowired
    private UserInfoMapperXML userInfoMapperXML;
    @Test
    void selectAll() {
        userInfoMapperXML.selectAll().stream().forEach(x-> System.out.println(x));
    }

    @Test

    void selectAll2() {
        userInfoMapperXML.selectAll2().stream().forEach(x-> System.out.println(x));
    }

    @Test
    void insertUser() {
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("陈奕安");
        userInfo.setPassword("203816");
        userInfo.setAge(22);
        userInfoMapperXML.insertUser(userInfo);
    }

    @Test
    void insertUser2() {
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("陈奕安33");
        userInfo.setPassword("203816");
        userInfo.setAge(22);
        Integer integer = userInfoMapperXML.insertUser2(userInfo);
        System.out.println("影响行数："+integer+" 自增id:"+ userInfo.getId());
    }

    @Test
    void updateUser() {
        userInfoMapperXML.updateUser("123",8,12);
    }

    @Test
    void deleteUser() {
        userInfoMapperXML.deleteUser(12);
    }

    @Test
    void insertUser3() {
        UserInfo userInfo=new UserInfo();
      userInfo.setUsername("陈奕安11");
        userInfo.setPassword("203816");
       userInfo.setAge(22);
    userInfo.setGender(1);
      //  userInfo.setPhone("19913236857");
        userInfoMapperXML.insertUser3(userInfo);
    }

    @Test
    void selectByCindition() {
        UserInfo userInfo=new UserInfo();
        userInfo.setPhone("1001");
       userInfo.setDeleteFlag(0);
        userInfoMapperXML.selectByCindition(userInfo).stream().forEach(x-> System.out.println(x));
    }
}