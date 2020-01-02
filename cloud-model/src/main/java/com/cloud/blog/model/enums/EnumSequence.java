package com.cloud.blog.model.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author myk
 * @Description //sequence 得序列
 * @Date 11:04 2019/12/25
 * @Param
 * @return
 **/
@Getter
public enum EnumSequence {


    BLOG_SEQUENCE_USERNAME(10,"rand_user");



    private Integer value;

    private String name;

    private static Map<Integer, String> map;


    EnumSequence(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Map<Integer, String> toMap() {
        if (map == null) {
            EnumSequence[] ary = EnumSequence.values();
            Map<Integer, String> enumMap = new HashMap<>();
            for (EnumSequence enumSequence : ary) {
                enumMap.put(enumSequence.getValue(), enumSequence.getName());
            }
            map = enumMap;
        }
        return map;
    }
    public static String getNameByValue(Integer value) {
        return toMap().get(value);
    }

}
