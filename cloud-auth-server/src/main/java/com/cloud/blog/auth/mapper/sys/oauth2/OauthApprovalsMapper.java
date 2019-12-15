package com.cloud.blog.auth.mapper.sys.oauth2;

import com.cloud.blog.model.po.sys.oauth2.OauthApprovals;

public interface OauthApprovalsMapper {
    int insert(OauthApprovals record);

    int insertSelective(OauthApprovals record);
}