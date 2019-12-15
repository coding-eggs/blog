package com.cloud.blog.datasource.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName MultiRouteDataSource
 * @Description 重写的函数决定了最后选择的DataSource
 * @Author wsail
 * @Date 2019/12/4 9:56
 **/

@Slf4j
public class MultiRouteDataSource extends AbstractRoutingDataSource {

    //数据源路由器，获取最终被执行的数据源

    @Override
    protected Object determineCurrentLookupKey() {
        //通过绑定线程的数据源上下文实现多数据源的动态切换

        String dataSource = DataSourceContext.getDataSource();

        logger.info("------------------当前数据源:{}---------------------"+ dataSource);
        return DataSourceContext.getDataSource();
    }
}
