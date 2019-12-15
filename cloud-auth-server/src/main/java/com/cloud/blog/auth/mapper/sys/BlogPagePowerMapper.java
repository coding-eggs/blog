package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogPagePower;

public interface BlogPagePowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogPagePower record);

    int insertSelective(BlogPagePower record);

    BlogPagePower selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogPagePower record);

    int updateByPrimaryKey(BlogPagePower record);
}