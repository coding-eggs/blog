package com.cloud.blog.auth.mapper.sys.oauth2;

import com.cloud.blog.model.po.sys.oauth2.OauthRefreshToken;

public interface OauthRefreshTokenMapper {
    int insert(OauthRefreshToken record);

    int insertSelective(OauthRefreshToken record);
}