package com.charlie.furn.bean;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;

public class Furn {
    private Integer id;

    private String name;

    private String maker;

    private BigDecimal price;

    private Integer sales;

    private Integer stock;

    // 当创建Furn对象 imgPath 为null时，imgPath给默认值(默认图片路径)
    private String imgPath = "assets/images/product-image/1.jpg";

    public Furn() {}

    public Furn(Integer id, String name, String maker, BigDecimal price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        // 如果imgPath不为null，而且是有数据的，就设置 this.imgPath，否则就使用默认值
        // imgPath != null && !imgPath.equals("") => 使用一个工具类的方法完成
        /**
         *     public static boolean hasText(@Nullable String str) {
         *         return str != null && !str.isEmpty() && containsText(str);
         *     }
         *     1. StringUtils.hasText(imgPath)就是要求imgPath不是null，而且不是""，而且不是"    "(全部空格)
         *     2. 该方法以后会经常使用
         */
        if (StringUtils.hasText(imgPath)) {
            this.imgPath = imgPath;
        }
    }

    @Override
    public String toString() {
        return "Furn{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maker='" + maker + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }
}