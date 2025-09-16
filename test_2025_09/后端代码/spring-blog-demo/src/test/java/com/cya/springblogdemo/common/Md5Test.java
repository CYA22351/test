package com.cya.springblogdemo.common;

import com.cya.springblogdemo.util.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/15 14:52
 * @description：
 * @modified By：
 * @version:
 */
public class Md5Test {
    public static void main(String[] args) {
        String password="123456";
//        String s = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
//        System.out.println(s);
        String salt=UUID.randomUUID().toString().replace("-","");
        System.out.println(salt);
        String s1 = DigestUtils.md5DigestAsHex((salt + password).getBytes(StandardCharsets.UTF_8));
        System.out.println(salt+s1);
    }
    @Test
    public void check(){
        String sql="4f120570e9fe4275947841cf40872887685c0bb9b3504e056a047a1ce89391e3";
        String intputPassword="123456";
        String  salt=sql.substring(0,32);
        //盐值+密码加密成密文
        String s = DigestUtils.md5DigestAsHex((salt + intputPassword).getBytes(StandardCharsets.UTF_8));
        System.out.println(sql.equals(salt+s));
    }

    @Test
    void test(){
        String encrypt= SecurityUtil.encrypt("123456");
        System.out.println(encrypt);
        boolean verify=SecurityUtil.verify("1234",encrypt);

    }

}