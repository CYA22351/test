package com.cya.example.springprinciple;

import com.cya.config.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//@EnableBiteConfig
//@Import(MySelector.class)
//@ComponentScan(basePackages = "com.cya.config")//扫描路径
//@Import({BiteConfig.class, BiteConfig2.class})
@Import(MyRegistrar.class)
@SpringBootApplication
public class SpringPrincipleApplication {

    public static void main(String[] args) {

     ApplicationContext context= SpringApplication.run(SpringPrincipleApplication.class, args);
        BiteConfig bean = context.getBean(BiteConfig.class);
//        BiteConfig2 biteConfig2=context.getBean(BiteConfig2.class);
        bean.study();
//        biteConfig2.study();

    }

}
