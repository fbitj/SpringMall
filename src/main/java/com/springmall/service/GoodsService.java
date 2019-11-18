package com.springmall.service;

import com.springmall.bean.*;

import java.util.List;

public interface GoodsService {
    /**
     * 分页查询商品
     * @param page 当前页
     * @param limit 每页显示数量
     * @param goodsSn 商品编号
     * @param name 商品名
     * @param sortField 排序字段
     * @param order 排序规则
     * @return
     */
    List<Goods> queryGoodsByPage(String page, String limit,String goodsSn,String name, String sortField, String order);

    /**
     * 逻辑删除商品
     * @param goods
     * @return
     */
    int deleteGoods(Goods goods);

    /**
     * 查询商品商品信息
     * @param id 商品id
     * @return
     */
    GoodInfoData queryGoodsDetailByGoodsId(int id);

    /**
     * 添加商品，以及商品详情
     * @param goods 商品信息
     * @param specifications 商品规格
     * @param products 商品表
     * @param attributes 商品参数
     * @return
     */
    int createGoods(Goods goods, List<Goods_specification> specifications, List<Goods_product> products, List<Goods_attribute> attributes);

    /**
     * 更新商品信息
     * @param goods
     * @param specifications
     * @param products
     * @param attributes
     */
    void updateGoodsInfo(Goods goods, List<Goods_specification> specifications, List<Goods_product> products, List<Goods_attribute> attributes);
}
