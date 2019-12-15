package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogUserMapper {
    int deleteByPrimaryKey(@Param("id") Long id, @Param("username") String username, @Param("identifyType") Integer identifyType, @Param("credential") String credential);

    int insert(BlogUser record);

    int insertSelective(BlogUser record);

    BlogUser selectByPrimaryKey(@Param("id") Long id, @Param("username") String username, @Param("identifyType") Integer identifyType, @Param("credential") String credential);

    int updateByPrimaryKeySelective(BlogUser record);

    int updateByPrimaryKey(BlogUser record);

    BlogUser selectUserByUsername(@Param("username") String username);
}