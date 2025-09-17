package com.cya.example.springprinciple.component;

import com.cya.example.springprinciple.model.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/17 11:28
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class DogComponent {

    @Bean
    public Dog dog(){
        return new Dog();
    }
    @Bean
    public Dog singleDog(){
        return new Dog();
    }

    @Bean
    @Scope("prototype")
    public Dog prototypeDog(){
        return new Dog();
    }

    @Bean
    @RequestScope
    public Dog requestDog(){
        return new Dog();
    }

    @Bean
    @SessionScope
    public Dog sessionDog(){
        return new Dog();
    }

    @Bean
    @ApplicationScope
    public Dog applicationDog(){
        return new Dog();
    }

}