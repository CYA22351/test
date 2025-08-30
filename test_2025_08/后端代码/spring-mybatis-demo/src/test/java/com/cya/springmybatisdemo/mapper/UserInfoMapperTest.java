package com.cya.springmybatisdemo.mapper;

import com.cya.springmybatisdemo.model.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    void selectALL() {
        System.out.println(userInfoMapper.selectALL());
    }

    @BeforeEach
    void setUp() {
        System.out.println("测试前");
    }

    @AfterEach
    void tearDown() {
        System.out.println("测试后");
    }


    @Test
    void selectAllById() {
        System.out.println(userInfoMapper.selectAllById(1));
    }

    @Test
    void selectByNameAndPassword() {
        userInfoMapper.selectByNameAndPassword("zhangsan","zhangsan").stream().forEach(x-> System.out.println(x));
    }

    @Test
    void insertUser() {
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("chenyian3");
        userInfo.setPassword("chenyian3");
        userInfo.setAge(22);
        Integer result=userInfoMapper.insertUser(userInfo);
        System.out.println("影响行数："+result+".id:"+userInfo.getId());

    }

    @Test
    void insertUser2() {
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("chenyian4");
        userInfo.setPassword("chenyian5");
        userInfo.setAge(22);
        Integer result=userInfoMapper.insertUser(userInfo);
        System.out.println("影响行数："+result+".id:"+userInfo.getId());
    }

    @Test
    void deleteUser() {
        userInfoMapper.deleteUser(8);
    }

    @Test
    void updateUser() {
        UserInfo userInfo=new UserInfo();
        userInfo.setId(7);
        userInfo.setDeleteFlag(1);
        userInfo.setPhone("19913236857");
        userInfoMapper.updateUser(userInfo);
    }


}