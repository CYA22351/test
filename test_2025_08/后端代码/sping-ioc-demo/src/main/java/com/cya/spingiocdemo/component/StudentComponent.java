package com.cya.spingiocdemo.component;

import com.cya.spingiocdemo.model.Student;
import org.springframework.context.annotation.Bean;
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
    @Bean({"s3","s4"})
//    @Bean(value = {"s3","s4"})
//    @Bean(name = {"s3","s4"})

    public Student s1(){
        return new Student("张三",11);
    }
    @Bean
    private String name(){
        return "lisi";
    }
    //传参
    @Bean
    public Student s2(Student s3){//Spring会从容器中查找String类型的对象
        return new Student(s3.getName(),21);
    }
}