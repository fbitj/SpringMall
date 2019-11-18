package com.springmall.mapper;

import com.springmall.bean.Attribute;
import com.springmall.bean.AttributeExample;
import com.springmall.bean.Goods_attribute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttributeMapper {
    long countByExample(AttributeExample example);

    int deleteByExample(AttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Attribute record);

    int insertSelective(Attribute record);

    List<Attribute> selectByExample(AttributeExample example);

    Attribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Attribute record, @Param("example") AttributeExample example);

    int updateByExample(@Param("record") Attribute record, @Param("example") AttributeExample example);

    int updateByPrimaryKeySelective(Attribute record);

    int updateByPrimaryKey(Attribute record);

    /**
     * 插入多条attribute，插入字段值为空则该字段为空
     * @param attributes
     */
    void insertAttributesSelective(@Param("attributes") List<Goods_attribute> attributes);
}