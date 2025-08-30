package com.cya.springmybatisdemo.mapper;

import com.cya.springmybatisdemo.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/30 17:16
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface ArticleInfoMapper {

    ArticleInfo selectArticleInfoByid(Integer id);
}