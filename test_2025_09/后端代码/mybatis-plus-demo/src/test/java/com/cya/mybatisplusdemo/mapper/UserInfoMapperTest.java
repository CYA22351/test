package com.cya.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cya.mybatisplusdemo.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoMapperTest {

@Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void insert(){
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("admin");
        userInfo.setPassword("123456");
        userInfo.setAge(14);
        userInfo.setPhone("110");
        userInfoMapper.insert(userInfo);
    }

    @Test
    void delete(){
        userInfoMapper.deleteById(5);
    }

    @Test
    void update(){
        UserInfo userInfo=new UserInfo();
        userInfo.setId(4);
        userInfo.setUsername("admin444");
        userInfo.setPassword("123456");
        userInfo.setAge(22);

        userInfoMapper.updateById(userInfo);
    }

    @Test
    void select(){
        UserInfo userInfo = userInfoMapper.selectById(4);
        System.out.println(userInfo);
    }

    //QueryWrapper
    @Test
    void selectByCondition(){
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("id,username,password,age")
                .eq("age",18)
                .like("username","min");
        userInfoMapper.selectList(queryWrapper).stream().forEach(x-> System.out.println(x));
    }

    //lambdaQueryWrapper
    @Test
    void selectByCondition2(){
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().select(UserInfo::getId,UserInfo::getUsername,UserInfo::getPassword,UserInfo::getAge,UserInfo::getDeleteFlag)
                        .eq(UserInfo::getAge,15)
                                .like(UserInfo::getUsername,"min");
        userInfoMapper.selectList(queryWrapper).stream().forEach(x-> System.out.println(x));
    }

    //QueryWrapper
    @Test
    void updateByCondition(){
        UserInfo userInfo=new UserInfo();
        userInfo.setDeleteFlag(1);
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lt("age",20);//age<20
        userInfoMapper.update(userInfo,queryWrapper) ;


    }
    @Test
    void updateByCondition2(){
        UpdateWrapper<UserInfo> infoUpdateWrapper=new UpdateWrapper<>();
        infoUpdateWrapper.set("delete_flag",0).set("age",5)//只有update才能set
                        .in("id", List.of(1,2,3));
        userInfoMapper.update(infoUpdateWrapper) ;


    }


    @Test
    void updateByCondition3(){
        UpdateWrapper<UserInfo> infoUpdateWrapper=new UpdateWrapper<>();
        infoUpdateWrapper.setSql("age=age+10")//只有update才能set
                .in("id", List.of(1,2,3));
        userInfoMapper.update(infoUpdateWrapper) ;


    }

    //LambdaUpdateWrapper
    @Test
    void updateByCondition4(){

        UpdateWrapper<UserInfo> infoUpdateWrapper=new UpdateWrapper<>();
        infoUpdateWrapper.lambda().set(UserInfo::getDeleteFlag,0)
                        .lt(UserInfo::getAge,20);
//        infoUpdateWrapper.setSql("age=age+10")//只有update才能set
//                .in("id", List.of(1,2,3));
        userInfoMapper.update(infoUpdateWrapper) ;


    }

    @Test
    void deleteByCondition(){
        UpdateWrapper<UserInfo> userInfoUpdateWrapper=new UpdateWrapper<>();
        userInfoUpdateWrapper.eq("age",22);
        userInfoMapper.delete(userInfoUpdateWrapper);
    }

    @Test
    void selectById2() {
        UserInfo userInfo=userInfoMapper.selectById2(2);
        System.out.println(userInfo);
    }

    @Test
    void selectById3() {
        UserInfo userInfo=userInfoMapper.selectById3(3);
        System.out.println(userInfo);
    }

    @Test
    void selectUserInfoByCondition() {
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lt("age",20);
        userInfoMapper.selectUserInfoByCondition(queryWrapper).stream().forEach(x-> System.out.println(x));
    }

    @Test
    void updateById2() {
        //where id in (1,2,3)

        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("id",List.of(1,2,3));
        userInfoMapper.updateById2(10,queryWrapper);
    }
}