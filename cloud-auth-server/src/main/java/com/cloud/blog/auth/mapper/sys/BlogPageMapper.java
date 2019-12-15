package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogPage;

public interface BlogPageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogPage record);

    int insertSelective(BlogPage record);

    BlogPage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogPage record);

    int updateByPrimaryKey(BlogPage record);
}