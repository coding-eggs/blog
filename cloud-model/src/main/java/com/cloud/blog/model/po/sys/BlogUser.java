package com.cloud.blog.model.po.sys;

import lombok.Data;

import java.util.Date;

@Data
public class BlogUser {
    private Long id;

    private String username;

    private Integer identifyType;

    private String credential;

    private String nickname;

    private Date birthday;

    private String figureUrl;

    private Byte sex;

    private String phone;

    private String mail;

    private Date lastLoginTime;

    private Date createTime;

    private Date modifyTime;

    private Date lastPasswordResetTime;

    private Byte isLock;

    private Byte isDelete;


}