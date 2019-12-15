package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogFile;

public interface BlogFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogFile record);

    int insertSelective(BlogFile record);

    BlogFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogFile record);

    int updateByPrimaryKey(BlogFile record);
}