package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogPower;

public interface BlogPowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogPower record);

    int insertSelective(BlogPower record);

    BlogPower selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogPower record);

    int updateByPrimaryKey(BlogPower record);
}