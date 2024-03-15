package com.charlie.test.service;

import com.charlie.furn.bean.Furn;
import com.charlie.furn.service.FurnService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

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
        // furnService=class com.sun.proxy.$Proxy22
        System.out.println("furnService=" + furnService.getClass());
    }

    @Test
    public void save() {
        Furn furn = new Furn(null, "郁金香盆栽", "青植", new BigDecimal("26.5"), 1236, 64, "assets/images/product-image/9.jpg");
        furnService.save(furn);
        System.out.println("OK!");
    }

    @Test
    public void findAll() {
        List<Furn> furnList = furnService.findAll();
        for (Furn furn : furnList) {
            System.out.println("furn=" + furn);
        }
        System.out.println("OK!");
    }

    @Test
    public void update() {
        Furn furn = new Furn();
        furn.setId(14);
        furn.setName("大萝卜头空调");
        furn.setPrice(new BigDecimal(3999));
        furn.setSales(35);
        furn.setStock(166);
        // 因为图片imgPath有一个默认值，如果不想修改该值的话，可以显示地将其设置为null(仅限于有默认值的字段)
        furn.setImgPath(null);
        furnService.update(furn);
        System.out.println("OK!");
    }

    @Test
    public void del() {
        furnService.del(16);
        System.out.println("OK!");
    }

    @Test
    public void getById() {
        Furn furn = furnService.findFurnById(4);
        System.out.println("furn=" + furn);
    }

    @Test
    public void findByCondition() {
        // 传入空串或全空格字符串，执行查询全部操作
        List<Furn> furnList = furnService.findByCondition("   ");
        for (Furn furn : furnList) {
            System.out.println("furn=" + furn);
        }
        System.out.println("OK!");
    }
}
