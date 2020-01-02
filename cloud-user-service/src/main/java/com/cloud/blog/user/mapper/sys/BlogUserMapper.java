package com.cloud.blog.user.mapper.sys;


import com.cloud.blog.model.po.sys.BlogUser;
import com.cloud.blog.user.config.pojo.BaseSecurityUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogUserMapper {

    int insert(BlogUser record);

    int insertSelective(BlogUser record);

    int updateByPrimaryKeySelective(BlogUser record);

    int updateByPrimaryKey(BlogUser record);

    BaseSecurityUser selectUserByUsername(@Param("username") String username);

    int insertBlogUser(BlogUser blogUser);

    int insertBlogUserPhone(BlogUser blogUser);

    int selectCountUsername(@Param("username") String username,@Param("identifyType") Integer identifyType);

    int selectQQLoginInfo(@Param("openId") String openId,@Param("identifyType") Integer identifyType);
}
