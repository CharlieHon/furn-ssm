package com.charlie.test.service;

import com.charlie.furn.bean.Furn;
import com.charlie.furn.service.FurnService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

public class FurnServiceTest {
    // 属性
    private ApplicationContext ioc;
    // 从spring容器中，获取的是FurnService接口对象(代理对象)
    private FurnService furnService;

    @Before
    public void init() {
        ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 说明：通过 FurnService.class 类型获取 FurnService接口对象/代理对象
        furnService = ioc.getBean(FurnService.class);
        System.out.println("furnService=" + furnService.getClass());
    }

    @Test
    public void t1() {
        Furn furn = new Furn(null, "4K27寸显示器", "飞利浦", new BigDecimal("2560.5"), 560, 140, "assets/images/product-image/9.jpg");
        furnService.save(furn);
        System.out.println("OK!");
    }

}
