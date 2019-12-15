package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogOperation;

public interface BlogOperationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogOperation record);

    int insertSelective(BlogOperation record);

    BlogOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogOperation record);

    int updateByPrimaryKey(BlogOperation record);
}