package com.springmall.bean;

import lombok.Data;

import java.util.List;

@Data
public class CreateGoods {

    /**
     * goods : {"picUrl":"","gallery":[],"goodsSn":"1231","name":"21321","counterPrice":"1231","retailPrice":"123123","isNew":true,"isHot":false,"isOnSale":true,"unit":"3123","keywords":"321312","categoryId":1013003,"brandId":1001002,"brief":"12312"}
     * specifications : [{"specification":"3123","value":"12312","picUrl":""}]
     * products : [{"id":0,"specifications":["12312"],"price":0,"number":0,"url":""}]
     * attributes : [{"attribute":"3123","value":"21312"}]
     */

    private Goods goods;
    private List<Goods_specification> specifications;
    private List<Goods_product> products;
    private List<Goods_attribute> attributes;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<Goods_specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Goods_specification> specifications) {
        this.specifications = specifications;
    }

    public List<Goods_product> getProducts() {
        return products;
    }

    public void setProducts(List<Goods_product> products) {
        this.products = products;
    }

    public List<Goods_attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Goods_attribute> attributes) {
        this.attributes = attributes;
    }








}
