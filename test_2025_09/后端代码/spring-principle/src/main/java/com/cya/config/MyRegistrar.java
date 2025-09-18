package com.cya.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/18 16:37
 * @description：
 * @modified By：
 * @version:
 */
public class MyRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        GenericBeanDefinition beanDefinition=new GenericBeanDefinition();
        beanDefinition.setBeanClass(BiteConfig.class);
        registry.registerBeanDefinition("biteConfig",beanDefinition);
    }
}