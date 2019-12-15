package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogRole record);

    int insertSelective(BlogRole record);

    BlogRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogRole record);

    int updateByPrimaryKey(BlogRole record);

    List<BlogRole> selectRoleListByUserId(@Param("userId") Long userId);
}