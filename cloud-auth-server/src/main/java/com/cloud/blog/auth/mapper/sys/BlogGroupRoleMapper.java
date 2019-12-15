package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogGroupRole;

public interface BlogGroupRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogGroupRole record);

    int insertSelective(BlogGroupRole record);

    BlogGroupRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogGroupRole record);

    int updateByPrimaryKey(BlogGroupRole record);
}