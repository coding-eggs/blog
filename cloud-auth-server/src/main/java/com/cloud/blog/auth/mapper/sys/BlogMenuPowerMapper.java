package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogMenuPower;

public interface BlogMenuPowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogMenuPower record);

    int insertSelective(BlogMenuPower record);

    BlogMenuPower selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogMenuPower record);

    int updateByPrimaryKey(BlogMenuPower record);
}