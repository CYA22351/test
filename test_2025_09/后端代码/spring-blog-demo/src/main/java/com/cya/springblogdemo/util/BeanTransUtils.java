package com.cya.springblogdemo.util;

import com.cya.springblogdemo.pojo.dataobject.BlogInfo;
import com.cya.springblogdemo.pojo.dataobject.UserInfo;
import com.cya.springblogdemo.pojo.request.UpdateBlogRequest;
import com.cya.springblogdemo.pojo.response.BlogInfoResopnse;
import com.cya.springblogdemo.pojo.response.UserInfoResponse;
import org.springframework.beans.BeanUtils;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/13 15:42
 * @description：
 * @modified By：
 * @version:
 */
public class BeanTransUtils {

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

    public static UserInfoResponse trans(UserInfo userInfo){
        if (userInfo==null){
            //TODO:待做事项
            return null;
        }
        UserInfoResponse userInfoResponse = new UserInfoResponse();

        //将blogInfo的格式转为BlogInfoResopnse
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    public static BlogInfo trans(UpdateBlogRequest updateBlogRequest){
        if (updateBlogRequest==null){
            //TODO:待做事项
            return null;
        }
       BlogInfo blogInfo=new BlogInfo();
        BeanUtils.copyProperties(updateBlogRequest,blogInfo);
      return blogInfo;
    }

}