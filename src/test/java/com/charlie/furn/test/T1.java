package com.charlie.furn.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class T1 {

    @Test
    public void t1() {
        // 看看spring配置的Bean是否可以获取到
        // 1. 获取到容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object bean = ioc.getBean("pooledDataSource");
        System.out.println("bean=" + bean);
        System.out.println(ioc.getBean("sqlSessionFactory"));
    }
}
