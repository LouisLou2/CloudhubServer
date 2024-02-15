package com.leo.cloudhubserver.config;

import org.springframework.context.annotation.Configuration;
@Configuration
public class MybatisPlusConfig {
    //以下两个组件，在3.2版本已经移除，如果要使用，请用显得方案，打印SQL语句执行时间的操作浪费，生产环境不用
    ///**
    // * mybatis-plus SQL执行效率插件【生产环境可以关闭】
    // */
    //@Bean
    //public PerformanceInterceptor performanceInterceptor() {
    //    return new PerformanceInterceptor();
    //}
    //
    ///**
    // * 分页插件
    // */
    //@Bean
    //public PaginationInterceptor paginationInterceptor() {
    //    return new PaginationInterceptor();
    //}
}