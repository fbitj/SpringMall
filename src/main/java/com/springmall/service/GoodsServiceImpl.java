package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.springmall.bean.*;
import com.springmall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    AttributeMapper attributeMapper;
    @Autowired
    Goods_attributeMapper goodsAttributeMapper;
    @Autowired
    Goods_specificationMapper goodsSpecificationMapper;
    @Autowired
    Goods_productMapper productMapper;

    @Override
    @Transactional
    public void updateGoodsInfo(Goods goods, List<Goods_specification> specifications, List<Goods_product> products, List<Goods_attribute> attributes) {
        goodsMapper.updateByPrimaryKeySelective(goods);/*
        productMapper.updateProductsByPrimaryKeySelective(products);
        attributeMapper.updateAttributesByPrimaryKeySelective(attributes);
        goodsSpecificationMapper.updateSpecificationsByPrimaryKeySelective(specifications);*/
        for (Goods_specification specification : specifications) {
            goodsSpecificationMapper.updateByPrimaryKey(specification);
        }
        for (Goods_product product : products) {
            productMapper.updateByPrimaryKey(product);
        }
        for (Goods_attribute attribute : attributes) {
            goodsAttributeMapper.updateByPrimaryKey(attribute);
        }
    }

    @Override
    @Transactional
    public int createGoods(Goods goods, List<Goods_specification> specifications, List<Goods_product> products, List<Goods_attribute> attributes) {
        goodsMapper.insertSelective(goods);
        Integer id = goods.getId();
        for (Goods_product product : products) {
            product.setGoodsId(id);
        }
        productMapper.insertProductsSelective(products);
        for (Goods_attribute attribute : attributes) {
            attribute.setGoodsId(id);
        }
        attributeMapper.insertAttributesSelective(attributes);
        for (Goods_specification specification : specifications) {
            specification.setGoodsId(id);
        }
        goodsSpecificationMapper.insertSpecificationsSelective(specifications);
//        goodsSpecificationMapper.insertSelective(specifications);
        return 1;
    }

    @Override
    public GoodInfoData queryGoodsDetailByGoodsId(int id) {
        GoodInfoData goodInfoData = new GoodInfoData();
        // 查询goods表
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        // 查询attribute表
        AttributeExample attributeExample = new AttributeExample();
        attributeExample.createCriteria().andGoodsIdEqualTo(id);
        List<Attribute> attributes = attributeMapper.selectByExample(attributeExample);
        // 查询specifications表
        Goods_specificationExample goodsSpecificationExample = new Goods_specificationExample();
        goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(id);
        List<Goods_specification> specifications = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        // 查询products表
        Goods_productExample productExample = new Goods_productExample();
        productExample.createCriteria().andGoodsIdEqualTo(id);
        List<Goods_product> products = productMapper.selectByExample(productExample);
        // 封装数据
        Integer[] categoryIds = new Integer[]{goods.getId()};
        goodInfoData.setCategoryIds(categoryIds);
        goodInfoData.setGoods(goods);
        goodInfoData.setAttributes(attributes);
        goodInfoData.setSpecifications(specifications);
        goodInfoData.setProducts(products);
        return goodInfoData;
    }

    @Override
    public int deleteGoods(Goods goods) {
        goods.setDeleted(true);
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public List<Goods> queryGoodsByPage(String page, String limit, String goodsSn, String name, String sortField, String order) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause(sortField + " " + order);
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        // goodsSn或name不为空是才查询
        if (!StringUtils.isEmpty(goodsSn)) {
            criteria.andGoodsSnEqualTo(goodsSn);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }
}
