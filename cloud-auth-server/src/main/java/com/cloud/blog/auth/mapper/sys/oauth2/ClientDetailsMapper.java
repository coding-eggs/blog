package com.cloud.blog.auth.mapper.sys.oauth2;

import com.cloud.blog.model.po.sys.oauth2.ClientDetails;

public interface ClientDetailsMapper {
    int deleteByPrimaryKey(String appid);

    int insert(ClientDetails record);

    int insertSelective(ClientDetails record);

    ClientDetails selectByPrimaryKey(String appid);

    int updateByPrimaryKeySelective(ClientDetails record);

    int updateByPrimaryKey(ClientDetails record);
}