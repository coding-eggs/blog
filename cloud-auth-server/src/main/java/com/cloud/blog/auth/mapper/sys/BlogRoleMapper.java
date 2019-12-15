package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogRole;

public interface BlogRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogRole record);

    int insertSelective(BlogRole record);

    BlogRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogRole record);

    int updateByPrimaryKey(BlogRole record);
}