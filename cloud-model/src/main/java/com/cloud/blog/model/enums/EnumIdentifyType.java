package com.cloud.blog.model.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author myk
 * @Description //认证类型枚举
 * @Date 16:54 2019/12/24
 * @Param
 * @return
 **/
@Getter
public enum EnumIdentifyType {

    LOGIN_IDENTIFY_LOCAL(10,"本系统登录"),
    LOGIN_IDENTIFY_QQ(20,"QQ登录"),
    LOGIN_IDENTIFY_WECHAT(30,"微信登录"),
    LOGIN_IDENTIFY_PHONE(40,"手机登录"),
    LOGIN_IDENTIFY_MAIL(50,"邮箱登录");


    Integer value;
    String name;

    private static Map<Integer, String> map;



    EnumIdentifyType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Map<Integer, String> toMap() {
        if (map == null) {
            EnumIdentifyType[] ary = EnumIdentifyType.values();
            Map<Integer, String> enumMap = new HashMap<>();
            for (EnumIdentifyType enumIdentifyType : ary) {
                enumMap.put(enumIdentifyType.getValue(), enumIdentifyType.getName());
            }
            map = enumMap;
        }
        return map;
    }
    public static String getNameByValue(Integer value) {
        return toMap().get(value);
    }
}
