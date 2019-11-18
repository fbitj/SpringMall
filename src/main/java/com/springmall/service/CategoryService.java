package com.springmall.service;

import com.springmall.bean.CatAndBrandReqVo;
import com.springmall.bean.Category;
import com.springmall.bean.CategoryL1;
import com.springmall.bean.CategoryResp;

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
}
