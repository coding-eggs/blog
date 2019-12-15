package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogGroup;

public interface BlogGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogGroup record);

    int insertSelective(BlogGroup record);

    BlogGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogGroup record);

    int updateByPrimaryKey(BlogGroup record);
}