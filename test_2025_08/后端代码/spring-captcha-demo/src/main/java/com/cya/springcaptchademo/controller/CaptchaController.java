package com.cya.springcaptchademo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.cya.springcaptchademo.model.CaptchaP;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/18 11:16
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("/captcha")
@RestController
public class CaptchaController {

    @Autowired
    private CaptchaP captchaP;

    private final static Long vALID_TIME=60*1000L;

    @RequestMapping("/getCaptcha")
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {

        response.setContentType("image/jpeg");//设置编码
        response.setHeader("Pragma","No-cahce");

        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(captchaP.getWidth(), captchaP.getHeigh(), 4, 4);
        String code=captcha.getCode();
        //http是无状态的不记录信息，用session来记录生成验证码
        session.setAttribute(captchaP.getSession().getKey(),code);
        session.setAttribute(captchaP.getSession().getDate(),new Date());//验证码生成时间，用来判断是否超时
        //输出流，直接输出到浏览器
        captcha.write(response.getOutputStream());
        //关闭response
        response.getOutputStream().close();

    }
    @RequestMapping("/check")
    public boolean check(String captcha,HttpSession session){
            if (!StringUtils.hasLength(captcha)){
                return false;
            }
            String code=(String) session.getAttribute(captchaP.getSession().getKey());
            Date date=(Date) session.getAttribute(captchaP.getSession().getDate());
            if (captcha.equalsIgnoreCase(code) &&date!=null &&  System.currentTimeMillis()-date.getTime()<vALID_TIME){
                return true;

            }
return false;
    }
}