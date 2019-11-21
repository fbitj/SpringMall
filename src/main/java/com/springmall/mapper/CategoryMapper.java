package com.springmall.mapper;

import com.springmall.bean.Category;
import com.springmall.bean.CategoryExample;
import java.util.List;

import com.springmall.bean.Goods;
import com.springmall.bean.HomePageData;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectChannelCatagory();

//    根据一级分类id查询该类别的指定数目的商品数据
    List<Goods> selectFloorGoodsByCategory(Integer id, int amountLimit);
}