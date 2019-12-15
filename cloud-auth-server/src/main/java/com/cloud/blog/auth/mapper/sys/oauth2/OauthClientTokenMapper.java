package com.cloud.blog.auth.mapper.sys.oauth2;

import com.cloud.blog.model.po.sys.oauth2.OauthClientToken;

public interface OauthClientTokenMapper {
    int deleteByPrimaryKey(String authenticationId);

    int insert(OauthClientToken record);

    int insertSelective(OauthClientToken record);

    OauthClientToken selectByPrimaryKey(String authenticationId);

    int updateByPrimaryKeySelective(OauthClientToken record);

    int updateByPrimaryKeyWithBLOBs(OauthClientToken record);

    int updateByPrimaryKey(OauthClientToken record);
}