package com.cya.springbootdemo2.controller;

import com.cya.springbootdemo2.modle.userInfo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/7 22:20
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("request")
@RestController
public class RequestController {
    @RequestMapping("r0")
    public String r1(){
        return "hello world";
    }
    @RequestMapping("r1")
    public String r1( String keyword){

        return "接收参数:"+keyword;
    }
    @RequestMapping("r2")
    public String r2( String username,String password){
        return "接收参数： userName:"+username+", password: "+password;
    }
    // method = RequestMethod.GET等于@GetMapping
    @RequestMapping(value = "r3",method = RequestMethod.GET)
    public String r3(Integer number){
        return "接收参数:"+number;
    }
    //参数值不能为bull
    @RequestMapping("r4")
    public String r4( int  number){
        return "接收参数:"+number;
    }
    @RequestMapping("r5")
    public String r5(userInfo userInfo){
        return "接收参数：userInfo="+userInfo.toString();
    }
    //从前端接受参数q，赋值给keyword,一般为必传参数，设置为false
    @RequestMapping("r6")
    //@RequestParam("q") 必传参数
    public String r6(@RequestParam(value = "q",required = false) String keyword){
        return "接收参数："+keyword;
    }
    //传递数组
    @RequestMapping("r7")
    public String r7(String[] arr){
        return "接收参数："+ Arrays.toString(arr);
    }
    //传递集合
    @RequestMapping("r8")
    public String r7(@RequestParam List<Integer> arr){
        return "接收参数：list="+arr;
    }
    //传递json
    @RequestMapping("r9")
//@RequestBody，接受json类型的参数
    public String r9(@RequestBody userInfo userInfo){
        return userInfo.toString();
    }
    //从url中获取参数
    @RequestMapping("/articled/{articled}")
    public String r10(@PathVariable Integer articled){
return "获取文章ID："+articled;
    }
    @RequestMapping("/articled/{type}/{articled}")
    public String r11(@PathVariable Integer articled,@PathVariable("type") String articledtype){
        return "获取文章ID："+articled+"type:"+articledtype;
    }
    //上传文件
    @RequestMapping("r12")
    public String r12(@RequestPart("file11") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        //文件上传
        file.transferTo(new File("D:\\workspace\\" + file.getOriginalFilename()));
        return "文件上传成功";
    }
//获取cookie
    @RequestMapping("r13")
    //HttpServletRequest请求信息，HttpServletResponse响应信息
    public String r13(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
    if (cookies!=null){
        for (Cookie cookie:cookies){
            System.out.println(cookie.getName()+":"+cookie.getValue());
        }
    }
    return "成功返回cookie";
    }
    @RequestMapping("r14")
    public String r14(@CookieValue("java") String java){
        return "从cookie中获取Java的值："+java;
    }
    //Session的存储
    @RequestMapping("setSession")
    public String setSession(HttpServletRequest request,HttpServletResponse response){
        //从cookie中获取sessionid，根据sessionId获取session对象
        HttpSession session = request.getSession(true);//默认为true，如果没有成功获取SessionId，则创建一个空的Session对象
     //默认存储在内存中
        session.setAttribute("username","zhangsan");//键值对
        session.setAttribute("age",17);
        return "设置Session成功！";

    }
    @RequestMapping("getSession")
    public String getSession(HttpServletRequest request,HttpServletResponse response){
        //从cookie中获取sessionid，根据sessionId获取session对象
        //每一个用户都有一个Session对象，浏览器和postman有两个不一样的Session，用SessionIId区别
        HttpSession session = request.getSession(false);//默认为true，如果没有成功获取SessionId，则创建一个空的Session对象
      //如果用户登录，session有值，未登录，session为null
        if (session==null){
            return "用户未登录";
        }
        else {
            String Username=(String) session.getAttribute("username");
            return "登陆用户为："+Username;
        }


    }
    @RequestMapping("getSession2")
    public String getSession2(HttpSession httpSession){
        //从cookie中获取sessionid，根据sessionId获取session对象
        //每一个用户都有一个Session对象，浏览器和postman有两个不一样的Session，用SessionIId区别
       //默认为true，如果没有成功获取SessionId，则创建一个空的Session对象
        //如果用户登录，session有值，未登录，session为null

            String Username=(String) httpSession.getAttribute("username");
            return "登陆用户为："+Username;
        }


//利用注解
    @RequestMapping("getSession3")
    public String getSession3(@SessionAttribute("username") String username){
        //从cookie中获取sessionid，根据sessionId获取session对象
        //每一个用户都有一个Session对象，浏览器和postman有两个不一样的Session，用SessionIId区别

            return "登陆用户为："+username;



    }
    //获取header
    @RequestMapping("getHeader")
    public String getHeader(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        return "从header中获取userAgent："+userAgent;

    }
    @RequestMapping("getHeader2")
    public String getHeader2(@RequestHeader("User-Agent") String useragent){

        return "从header中获取userAgent："+useragent;

    }

}