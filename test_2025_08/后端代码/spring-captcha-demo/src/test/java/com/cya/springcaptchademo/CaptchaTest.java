package com.cya.springcaptchademo;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Console;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/18 10:47
 * @description：
 * @modified By：
 * @version:
 */
public class CaptchaTest {
    public static void main(String[] args) {
//定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

//图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write("d:/line.png");
        //输出code
        System.out.println(lineCaptcha.getCode());
        //验证图形验证码的有效性，返回boolean值
        System.out.println(lineCaptcha.verify("1234"));

        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
//CircleCaptcha captcha = new CircleCaptcha(200, 100, 4, 20);
//图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write("d:/circle.png");
//验证图形验证码的有效性，返回boolean值
        System.out.println(captcha.verify("1234"));

    }
}