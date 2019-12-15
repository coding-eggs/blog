package com.cloud.blog.datasource.aop;

import com.cloud.blog.datasource.config.datasource.DataSource;
import com.cloud.blog.datasource.config.datasource.DataSourceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 数据源切换控制切面 Data source toggle control aspect
 * @Auther: Ailuoli
 * @Date: 2019/2/23 16:54
 */
@Component
@Aspect
@Order(0) //保证先被执行
public class DataSourceAspect {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //模拟操作从表的一些方法
    private static String[] queryMethods = {"query","select","getAll","find"};
    private static String[] updateMethods = {"insert","add","delete","update"};
    private static List<String> queryMethod = new LinkedList<>(Arrays.asList(queryMethods));
    private static List<String> updateMethod = new LinkedList<>(Arrays.asList(updateMethods));
    //----------------------------------------------   用注解拦截   ----------------------------------------------
    /**
     *   注解拦截切面表达式 Annotations to intercept
     *   @annotation 用于拦截所有被该注解标注的方法 Used to intercept all methods annotated with this annotation
     *   @within  用于拦截被所有该注解标注的类 Used to intercept all classes annotated by this annotation
     * */
    @Pointcut("@annotation(com.cloud.blog.datasource.config.datasource.DataSource) || @within(com.cloud.blog.datasource.config.datasource.DataSource) ")
    public void pointcut() {}

    @Before("pointcut()")
    public void annotationMethodBefore(JoinPoint joinPoint){
        Class<?> clazz = joinPoint.getTarget().getClass();
        DataSource annotation = clazz.getAnnotation(DataSource.class);
        //先判断类上是否有DataSource注解，如果没有在判断方法上是否有注解
        if(annotation == null){//类上没有
            //获取方法上的注解
            Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
            annotation = method.getAnnotation(DataSource.class);
            //如果还是为null则退出，这次方法调用将使用默认的数据源
            if(annotation == null){
                return;
            }
        }
        //获取注解上得值
        String dataSourceName = annotation.dataSource();
        logger.debug("---------------------------切换到数据源:"+dataSourceName+"----------------------------------");
        //因为有默认数据源的存在，所以不用担心注解上的值无对应的数据源，当找不到指定数据源时，会使用默认的数据源
        DataSourceContext.setDataSource(dataSourceName);
    }
    //执行完切面后，将线程共享中的数据源名称清空 让程序使用默认数据源
    @After("pointcut()")
    public void annotationMethodAfter(JoinPoint joinPoint){
        DataSourceContext.clearDataSource();
    }

//    //---------------------------------基于AOP，拦截某种\某个类-------------------------------------------------------------
//    @Pointcut("execution( * com.datasource.service.impl.*Impl.*(..))")
//    public void pointcut2(){}
//
//    @Before("pointcut2()")
//    public void dataSourcePointCut(JoinPoint joinPoint) {
//        Class<?> aClass = joinPoint.getTarget().getClass();
//        DataSource annotation = aClass.getAnnotation(DataSource.class);
//        //取出类上的注解，并将ThreadLocal 中的数据源设置为指定的数据源， 当然 也可以按照业务需要不适用注解，直接固定某一数据源
//        if(annotation != null){
//            String dataSource = annotation.dataSource();
//            DataSourceContext.setDataSource(dataSource);
//        }
//    }
//    @After("pointcut2()")
//    public void annotationMethodAfter1(JoinPoint joinPoint){
//        DataSourceContext.clearDataSource();
//    }
//    //--------------针对方法，比如数据库做主从，select连接从表(dataSource1)，update（query，update，delete）连接主表(dataSource2)------------------------
//    @Pointcut("execution( * com.datasource.mapper.*Mapper.*(..))")
//    public void pointcut3(){}
//
//    @Before("pointcut3()")
//    public void byMethods(JoinPoint joinPoint){
//        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
//        String name = method.getName();
//        //判断是否查询相关方法（走从库）
//        boolean isQuery = queryMethod.contains(name);
//        //判断是否修改相关方法（走主库）
//        boolean isUpdate = updateMethod.contains(name);
//        if(isQuery){
//            DataSourceContext.setDataSource("dataSource1");
//        }else if(isUpdate){
//            DataSourceContext.setDataSource("dataSource2");
//        }
//    }
//
//    @After("pointcut3()")
//    public void byMethods(){
//        DataSourceContext.clearDataSource();
//    }
}
