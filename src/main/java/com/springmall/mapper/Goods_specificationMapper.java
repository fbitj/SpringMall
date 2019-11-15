package com.springmall.mapper;

import com.springmall.bean.Goods_specification;
import com.springmall.bean.Goods_specificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Goods_specificationMapper {
    long countByExample(Goods_specificationExample example);

    int deleteByExample(Goods_specificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods_specification record);

    int insertSelective(Goods_specification record);

    List<Goods_specification> selectByExample(Goods_specificationExample example);

    Goods_specification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods_specification record, @Param("example") Goods_specificationExample example);

    int updateByExample(@Param("record") Goods_specification record, @Param("example") Goods_specificationExample example);

    int updateByPrimaryKeySelective(Goods_specification record);

    int updateByPrimaryKey(Goods_specification record);
}