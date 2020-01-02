package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogFile record);

    int insertSelective(BlogFile record);

    BlogFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogFile record);

    int updateByPrimaryKey(BlogFile record);
}
