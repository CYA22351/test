package com.cya.springbootdemo2;

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
    @RequestMapping("r3")
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
    //从前端接受参数，赋值给keyword
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
    @RequestMapping("r9")

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
}