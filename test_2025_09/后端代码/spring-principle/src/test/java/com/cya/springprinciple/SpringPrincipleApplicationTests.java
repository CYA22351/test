package com.cya.springprinciple;

import com.cya.example.springprinciple.component.BeanLifeComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringPrincipleApplicationTests {

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {

//        Dog bean1 = context.getBean(Dog.class);
//
//
//        System.out.println(bean1);
//
//        Dog bean2 = context.getBean(Dog.class);
//
//
//        System.out.println(bean2);
        BeanLifeComponent beanLifeComponent=context.getBean(BeanLifeComponent.class);
        beanLifeComponent.use();
    }

}
