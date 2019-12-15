package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogUserRole;

public interface BlogUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogUserRole record);

    int insertSelective(BlogUserRole record);

    BlogUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogUserRole record);

    int updateByPrimaryKey(BlogUserRole record);
}