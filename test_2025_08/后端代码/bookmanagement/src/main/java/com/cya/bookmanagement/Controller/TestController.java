package com.cya.bookmanagement.Controller;

import com.cya.bookmanagement.entity.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/8 14:44
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("/test")
@RestController
public class TestController {
    @RequestMapping("/t1")
    public Integer t1(){
        int a=10/0;//除数不能为0
        return 1;
    }

    @RequestMapping("/t2")
    public boolean t2(){
        int[] aa={1,2,3};
        System.out.println(aa[4]);//数组越界
        return true;
    }
    @RequestMapping("/t3")
    public String t3(){
        String a=null;
        System.out.println(a.length());
        return "t3";
    }
}