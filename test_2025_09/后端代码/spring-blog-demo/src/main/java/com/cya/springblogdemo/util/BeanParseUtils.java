package com.cya.springblogdemo.util;

import com.cya.springblogdemo.pojo.dataobject.BlogInfo;
import com.cya.springblogdemo.pojo.response.BlogInfoResopnse;
import org.springframework.beans.BeanUtils;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/13 15:42
 * @description：
 * @modified By：
 * @version:
 */
public class BeanParseUtils {
    public static BlogInfoResopnse trans(BlogInfo blogInfo){
        if (blogInfo==null){
            //TODO:待做事项
            return null;
        }
        BlogInfoResopnse resopnse = new BlogInfoResopnse();

        //将blogInfo的格式转为BlogInfoResopnse
        BeanUtils.copyProperties(blogInfo, resopnse);
        return resopnse;
    }
}