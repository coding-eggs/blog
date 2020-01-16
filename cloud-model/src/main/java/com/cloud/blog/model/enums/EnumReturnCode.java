package com.cloud.blog.model.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author myk
 * @Description //返回码类型
 * @Date 12:07 2019/12/25
 **/
@Getter
public enum EnumReturnCode {

    SUCCESS(200,"成功"),

    FAILURE(10001,"失败"),

    POWER_ACCESS_UNDEFINE(401,"权限不足"),

    USERNAME_IS_EMPTY(10002,"用户名为空"),
    USERNAME_IS_EXIST(10003,"用户名已存在"),
    INVALID_ACCESS_TOKEN(10004,"invalid access_token"),



    NETWORK_ERROR(99999,"网络错误");

    private Integer code;

    private String msg;


    EnumReturnCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    private static Map<Integer, String> map;




    public static Map<Integer, String> toMap() {
        if (map == null) {
            EnumReturnCode[] ary = EnumReturnCode.values();
            Map<Integer, String> enumMap = new HashMap<>();
            for (EnumReturnCode enumReturnCode : ary) {
                enumMap.put(enumReturnCode.getCode(), enumReturnCode.getMsg());
            }
            map = enumMap;
        }
        return map;
    }
    public static String getNameByValue(Integer value) {
        return toMap().get(value);
    }
}
