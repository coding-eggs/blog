package com.cloud.blog.model.po.sys;

import lombok.Data;

@Data
public class BlogMenu {

    private Integer id;

    private String menuName;

    private String menuUrl;

    private Integer parentId;

}