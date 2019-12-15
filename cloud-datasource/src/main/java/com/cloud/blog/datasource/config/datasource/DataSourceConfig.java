package com.cloud.blog.datasource.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig
 * @Description TODO
 * @Author wsail
 * @Date 2019/12/4 11:07
 **/

@Configuration
public class DataSourceConfig {

    @Autowired
    private DBProperties dbProperties;


    @Bean(name = "dataSource")
    public DataSource dataSource() {
        //初始化自定义数据源集 Initializes the custom datasource set
        dbProperties.init();
        /**
         *  采用AbstractRoutingDataSource的对象包装多数据源
         *  Adopting AbstractRoutingDataSource object packing more data sources
         */
        MultiRouteDataSource dataSource = new MultiRouteDataSource();
        dataSource.setTargetDataSources(dbProperties.getDataSources());
        /**
         * 设置默认的数据源，当拿不到指定数据源或者未指定数据源时使用该配置
         * Sets the default data source and uses this configuration when the specified data source is not available or is not specified
         */
        dataSource.setDefaultTargetDataSource(dbProperties.getDefaultDataSource());
        return dataSource;
    }

    /**
     * 配置事务管理 Configuration transaction management
     * @return
     */
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
