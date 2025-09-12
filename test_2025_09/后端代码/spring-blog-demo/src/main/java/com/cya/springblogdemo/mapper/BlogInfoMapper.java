package com.cya.springblogdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cya.springblogdemo.pojo.dataobject.BlogInfo;
import com.cya.springblogdemo.pojo.dataobject.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 17:58
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface BlogInfoMapper extends BaseMapper<BlogInfo> {
}