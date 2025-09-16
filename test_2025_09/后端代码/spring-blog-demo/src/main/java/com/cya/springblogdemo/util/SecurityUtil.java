package com.cya.springblogdemo.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/15 15:34
 * @description：
 * @modified By：
 * @version:
 */
public class SecurityUtil {
    /**
     * 加密
     * @param
     * @return 盐值+密文
     */
    public static String encrypt(String password){
        String salt= UUID.randomUUID().toString().replace("-","");
        String securityPassword = DigestUtils.md5DigestAsHex((salt + password).getBytes(StandardCharsets.UTF_8));
        return salt+securityPassword;
    }

    /**
     * 验证
     * @param inputpassword
     * @return
     */
    public static boolean verify(String inputpassword,String sqlPassword){

        if (!StringUtils.hasLength(inputpassword)){
            return false;
        }
        if (sqlPassword==null||sqlPassword.length()!=64){
            return false;
        }
        String salt=sqlPassword.substring(0,32);
        String securityPassword = DigestUtils.md5DigestAsHex((salt + inputpassword).getBytes(StandardCharsets.UTF_8));
        return (salt+securityPassword).equals(sqlPassword);
    }

}