package com.cya.spingiocdemo;

import com.cya.spingiocdemo.component.UserComponent;
import com.cya.spingiocdemo.config.UserConfig;
import com.cya.spingiocdemo.controller.HelloController;
import com.cya.spingiocdemo.model.Student;
import com.cya.spingiocdemo.resp.UserRepository;
import com.cya.spingiocdemo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpingIocDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(SpingIocDemoApplication.class, args);
//		//根据类型获取对象，适合该类型的对象只有一个
//		HelloController bean = context.getBean(HelloController.class);
//		bean.print();
//		//根据名称获取对象
//		HelloController bean2 =(HelloController) context.getBean("helloController");//小驼峰
//		bean2.print();
//
//		HelloController bean3 = context.getBean("helloController", HelloController.class);
//		bean3.print();
//		System.out.println(bean);
//		System.out.println(bean2);
//		System.out.println(bean3);
//		UserService bean = context.getBean(UserService.class);
//		bean.print();
//		UserRepository bean1 = context.getBean(UserRepository.class);
//		bean1.print();
//		UserComponent bean2 = context.getBean(UserComponent.class);
//		bean2.print();
//		UserConfig bean3 = context.getBean(UserConfig.class);
//		bean3.print();

		//Bean
//		Student bean = (Student) context.getBean("s2");
//		System.out.println(bean);

		//DI注入
//		HelloController bean = context.getBean(HelloController.class);
//		bean.print();
		UserService bean = context.getBean(UserService.class);
		bean.print();

	}

}
