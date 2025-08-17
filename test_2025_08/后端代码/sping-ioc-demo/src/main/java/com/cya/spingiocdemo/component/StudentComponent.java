package com.cya.spingiocdemo.component;

import com.cya.spingiocdemo.model.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/14 17:12
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class StudentComponent {


//    @Bean(value = {"s3","s4"})
 //@Bean(name = {"s3","s4"})
    @Bean
    public Student s1(){
        return new Student("张三",11);
    }
    @Bean
    public String name2(){
        return "wangwu";
    }
    @Bean
public String name(){
        return "lisi";
}
    //传参
   // @Primary
    @Bean
    public Student s2(@Qualifier("name") String name){//Spring会从容器中查找String类型的对象
        return new Student(name,21);
    }
}