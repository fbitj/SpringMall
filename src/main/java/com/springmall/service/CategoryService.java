package com.springmall.service;

import com.springmall.bean.*;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有的分类信息并按等级归类
     *
     * @return
     */
    List<CatAndBrandReqVo> queryAllCategoryByLevel();

    List<CategoryResp> queryCategory();

    List<CategoryL1> ValueAndLabel();

    int deleteCategory(CategoryResp categoryResp);

    int addCategory(Category category);

    Category selectCategory(Category category);

    int updateCategory(Category category);

    List<Category> getChannelCatagory();

    List<Category> getCategoryByLevel(String level);

    List<Category> getSubCategoryByPid(Integer id);

    Category getCategoryById(Integer id);
}
