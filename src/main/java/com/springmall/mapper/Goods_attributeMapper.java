package com.springmall.mapper;

import com.springmall.bean.Goods_attribute;
import com.springmall.bean.Goods_attributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Goods_attributeMapper {
    long countByExample(Goods_attributeExample example);

    int deleteByExample(Goods_attributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods_attribute record);

    int insertSelective(Goods_attribute record);

    List<Goods_attribute> selectByExample(Goods_attributeExample example);

    Goods_attribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods_attribute record, @Param("example") Goods_attributeExample example);

    int updateByExample(@Param("record") Goods_attribute record, @Param("example") Goods_attributeExample example);

    int updateByPrimaryKeySelective(Goods_attribute record);

    int updateByPrimaryKey(Goods_attribute record);
    /**
     * 插入多条attribute，插入字段值为空则该字段为空
     * @param attributes
     */
    void insertAttributesSelective(@Param("attributes") List<Goods_attribute> attributes);
}