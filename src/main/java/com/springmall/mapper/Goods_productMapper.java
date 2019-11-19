package com.springmall.mapper;

import com.springmall.bean.Goods_product;
import com.springmall.bean.Goods_productExample;
import java.util.List;

import com.springmall.bean.Product;
import org.apache.ibatis.annotations.Param;

public interface Goods_productMapper {
    long countByExample(Goods_productExample example);

    int deleteByExample(Goods_productExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods_product record);

    int insertSelective(Goods_product record);

    List<Goods_product> selectByExample(Goods_productExample example);

    Goods_product selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods_product record, @Param("example") Goods_productExample example);

    int updateByExample(@Param("record") Goods_product record, @Param("example") Goods_productExample example);

    int updateByPrimaryKeySelective(Goods_product record);

    int updateByPrimaryKey(Goods_product record);

    /**
     * 根据是否有值添加商品
     * @param products
     */
    void insertProductsSelective(@Param("products") List<Goods_product> products);

}