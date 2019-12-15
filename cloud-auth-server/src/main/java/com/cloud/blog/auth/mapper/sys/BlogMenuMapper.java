package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogMenu;

public interface BlogMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogMenu record);

    int insertSelective(BlogMenu record);

    BlogMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogMenu record);

    int updateByPrimaryKey(BlogMenu record);
}