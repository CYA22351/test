package com.cya.springbootdemo2.controller;

import com.cya.springbootdemo2.modle.MessageInfo;
import com.cya.springbootdemo2.service.MessqgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/11 10:41
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("message")
@RestController
public class MessageController {
    @Autowired
    private MessqgeService messqgeService;
    private List<MessageInfo> messageInfos=new ArrayList<>();
//produces = "application/json",返回的是json
    //@RequestMapping(value = "publish",produces = "application/json")
    @RequestMapping(value = "publish")

    //@RequestBody表示请求参数为json
    public String publish(@RequestBody MessageInfo messageInfo){
        //如果不加@RequestBody，if判断语句会判断为空
        if (!StringUtils.hasLength(messageInfo.getFrom())
        ||!StringUtils.hasLength(messageInfo.getTo())
        ||!StringUtils.hasLength(messageInfo.getMessage())){
            return "{\"ok\":0}";
    }
        messqgeService.addMessage(messageInfo);
//     messageInfos.add(messageInfo);

        return "{\"ok\":1}";

}
@RequestMapping("getList")
public List<MessageInfo> getList(){
        return messqgeService.queryAll();
    }
}