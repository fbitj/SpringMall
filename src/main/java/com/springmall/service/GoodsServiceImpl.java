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
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 更新商品信息
     * @param goods
     * @param specifications
     * @param products
     * @param attributes
     */
    @Override
    @Transactional
    public void updateGoodsInfo(Goods goods, List<Goods_specification> specifications, List<Goods_product> products, List<Goods_attribute> attributes) {
        // 删除指定id的goods相关信息，然后重新写入：即更新
        goodsMapper.updateByPrimaryKeySelective(goods);
        //1.删除
        Integer id = goods.getId();
        if (specifications.size() != 0) {
            Goods_specificationExample specificationExample = new Goods_specificationExample();
            specificationExample.createCriteria().andGoodsIdEqualTo(id);
            goodsSpecificationMapper.deleteByExample(specificationExample);
            goodsSpecificationMapper.insertSpecificationsSelective(specifications);
        }
        if (products.size() !=0) {
            Goods_productExample productExample = new Goods_productExample();
            productExample.createCriteria().andGoodsIdEqualTo(id);
            productMapper.deleteByExample(productExample);
            for (Goods_product product : products) {
                product.setGoodsId(goods.getId());
            }
            productMapper.insertProductsSelective(products);
        }
        if (attributes.size() != 0) {
            AttributeExample attributeExample = new AttributeExample();
            attributeExample.createCriteria().andGoodsIdEqualTo(id);
            attributeMapper.deleteByExample(attributeExample);
            attributeMapper.insertAttributesSelective(attributes);
        }

        /*goodsMapper.updateByPrimaryKeySelective(goods);
        *//*productMapper.updateProductsByPrimaryKeySelective(products);
        attributeMapper.updateAttributesByPrimaryKeySelective(attributes);
        goodsSpecificationMapper.updateSpecificationsByPrimaryKeySelective(specifications);*//*
        for (Goods_specification specification : specifications) {
            goodsSpecificationMapper.updateByPrimaryKey(specification);
        }
        for (Goods_product product : products) {
            productMapper.updateByPrimaryKey(product);
        }
        for (Goods_attribute attribute : attributes) {
            goodsAttributeMapper.updateByPrimaryKey(attribute);
        }*/
    }

    /**
     *
     * @param goods 商品信息
     * @param specifications 商品规格
     * @param products 商品表
     * @param attributes 商品参数
     * @return
     */
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

    /**
     * 查询商品信息
     * @param id 商品id
     * @return
     */
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
        Integer categoryId = goods.getCategoryId();
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Integer[] categoryIds = new Integer[]{category.getPid(),category.getId()};
        goodInfoData.setCategoryIds(categoryIds);
        goodInfoData.setGoods(goods);
        goodInfoData.setAttributes(attributes);
        goodInfoData.setSpecifications(specifications);
        goodInfoData.setProducts(products);
        return goodInfoData;
    }

    /**
     * 逻辑删除商品
     * @param goods
     * @return
     */
    @Override
    public int deleteGoods(Goods goods) {
        goods.setDeleted(true);
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

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
