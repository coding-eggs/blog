package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogGroupUser;

public interface BlogGroupUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogGroupUser record);

    int insertSelective(BlogGroupUser record);

    BlogGroupUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogGroupUser record);

    int updateByPrimaryKey(BlogGroupUser record);
}