package com.cloud.blog.auth.mapper.sys.oauth2;

import com.cloud.blog.model.po.sys.oauth2.OauthCode;

public interface OauthCodeMapper {
    int insert(OauthCode record);

    int insertSelective(OauthCode record);
}