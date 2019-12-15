package com.cloud.blog.datasource.config.datasource;

/**
 * @ClassName DataSourceContext
 * @Description TODO
 * @Author wsail
 * @Date 2019/12/4 9:54
 **/


public class DataSourceContext {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String value){
        contextHolder.set(value);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }

}
