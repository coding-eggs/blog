package com.cloud.blog.model.po.sys;

import lombok.Data;

@Data
public class BlogOperation {

    private Integer id;

    private String operationCode;

    private String operationName;

    private String preResourceMapping;

    private Integer parentId;

}