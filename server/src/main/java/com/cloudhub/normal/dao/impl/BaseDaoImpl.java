package com.cloudhub.normal.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class BaseDaoImpl {

    public static org.springframework.jdbc.core.JdbcTemplate JdbcTemplateObject;

    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        JdbcTemplateObject = (org.springframework.jdbc.core.JdbcTemplate) context.getBean("JdbcTemplate");
    }
}
