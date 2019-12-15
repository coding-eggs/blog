package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogOperationPower;

public interface BlogOperationPowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogOperationPower record);

    int insertSelective(BlogOperationPower record);

    BlogOperationPower selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogOperationPower record);

    int updateByPrimaryKey(BlogOperationPower record);
}