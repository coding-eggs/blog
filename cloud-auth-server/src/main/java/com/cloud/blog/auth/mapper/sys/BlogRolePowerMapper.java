package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogRolePower;

public interface BlogRolePowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogRolePower record);

    int insertSelective(BlogRolePower record);

    BlogRolePower selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogRolePower record);

    int updateByPrimaryKey(BlogRolePower record);
}