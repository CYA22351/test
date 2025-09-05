package com.cya.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cya.mybatisplusdemo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/5 17:33
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}