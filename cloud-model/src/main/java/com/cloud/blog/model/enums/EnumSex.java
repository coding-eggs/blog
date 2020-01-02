package com.cloud.blog.model.enums;

import lombok.Getter;

/**
 * @ClassName EnumSex
 * @Description 性别枚举
 * @Author wsail
 * @Date 2019/12/30 16:37
 **/
@Getter
public enum  EnumSex {
    ENUM_MAN((byte) 1,"男"),

    ENUM_WOMAN((byte)2,"女"),

    ENUM_OTHER((byte)3,"其他");

    private byte value;

    private String name;


    EnumSex(byte value, String name) {
        this.value = value;
        this.name = name;
    }
}
