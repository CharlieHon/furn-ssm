package com.charlie.furn.test;

import com.charlie.furn.bean.Furn;
import com.charlie.furn.dao.FurnMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

public class FurnMapperTest {

    private FurnMapper furnMapper;

    @Before
    public void init() {
        // 1. 获取到容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2. 获取到FurnMapper
        furnMapper = ioc.getBean(FurnMapper.class);
        System.out.println("furnMapper=" + furnMapper.getClass());  // class com.sun.proxy.$Proxy17
    }

    @Test
    public void insertSelective() {
        // 3. 添加数据
        Furn furn = new Furn(null, "人体工学椅", "西昊", new BigDecimal("560.5"), 260, 140, "assets/images/product-image/8.jpg");
        int affected = furnMapper.insertSelective(furn);
        System.out.println("affected=" + affected);
        System.out.println("OK!");
    }

    @Test
    public void deleteByPrimaryKey() {
        // 根据id，删除数据
        int affected = furnMapper.deleteByPrimaryKey(3);
        System.out.println("affected=" + affected);
        System.out.println("OK!");
    }

    @Test
    public void updateByPrimaryKey() {
        Furn furn = new Furn();
        furn.setId(7);
        furn.setName("朴实无华大衣柜");

        // 会修改所有的字段，如果没有设置字段对应的属性，那么默认是null，然而数据库表字段设置 not null，所以会报错
        //furnMapper.updateByPrimaryKey();

        // updateByPrimaryKeySelective 根据对象属性值修改数据，只修改非null属性值对应的字段
        // 其它方法都是类似的
        int affected = furnMapper.updateByPrimaryKeySelective(furn);
        System.out.println("affected=" + affected);
        System.out.println("OK!");
    }

    @Test
    public void selectByPrimaryKey() {
        Furn furn = furnMapper.selectByPrimaryKey(1);
        System.out.println("furn=" + furn);
        System.out.println("OK!");
    }
}
