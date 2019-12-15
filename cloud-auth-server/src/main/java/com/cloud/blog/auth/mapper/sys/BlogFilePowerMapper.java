package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogFilePower;

public interface BlogFilePowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogFilePower record);

    int insertSelective(BlogFilePower record);

    BlogFilePower selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogFilePower record);

    int updateByPrimaryKey(BlogFilePower record);
}