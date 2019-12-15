package com.cloud.blog.model.po.sys.oauth2;

import lombok.Data;

@Data
public class OauthCode {

    private String code;

    private byte[] authentication;

}