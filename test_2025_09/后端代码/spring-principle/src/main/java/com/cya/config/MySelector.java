package com.cya.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/17 22:16
 * @description：
 * @modified By：
 * @version:
 */
public class MySelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.cya.config.BiteConfig","com.cya.config.BiteConfig2"};
    }
}