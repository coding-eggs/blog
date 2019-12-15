package com.cloud.blog.model.po.sys;

import lombok.Data;

import java.util.Date;

@Data
public class BlogOperationLog {

    private Long id;

    private Integer operationTypeId;

    private String operationContent;

    private Integer operationUserId;

    private Date operationTime;

}