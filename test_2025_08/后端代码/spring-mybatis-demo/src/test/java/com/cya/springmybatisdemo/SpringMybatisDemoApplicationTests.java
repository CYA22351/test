package com.cya.springmybatisdemo;

import com.cya.springmybatisdemo.mapper.UserInfoMapper;
import com.cya.springmybatisdemo.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class SpringMybatisDemoApplicationTests {
    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        UserInfoMapper bean = context.getBean(UserInfoMapper.class);
        //stream流配合lambda'表达式
//        bean.selectALL().stream().forEach(x-> System.out.println(x));
        List<UserInfo> userInfos = bean.selectALL();
        for (UserInfo userInfo:userInfos){
            System.out.println(userInfo);
        }
      //  List<UserInfo> collect = userInfos.stream().filter(x -> x.getGender() == 1).collect(Collectors.toList());

    }

}
