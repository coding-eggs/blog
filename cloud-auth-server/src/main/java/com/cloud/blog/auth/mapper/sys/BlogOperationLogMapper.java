package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogOperationLog;

public interface BlogOperationLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogOperationLog record);

    int insertSelective(BlogOperationLog record);

    BlogOperationLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogOperationLog record);

    int updateByPrimaryKey(BlogOperationLog record);
}