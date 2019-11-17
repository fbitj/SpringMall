package com.springmall.service;



import com.springmall.bean.Category;
import com.springmall.bean.CategoryL1;
import com.springmall.bean.CategoryResp;

import java.util.List;

public interface CategoryService {
    List<CategoryResp> queryCategory();
    List<CategoryL1> ValueAndLabel();
    int deleteCategory(CategoryResp categoryResp);
    int addCategory(Category category);
    Category selectCategory(Category category);
    int updateCategory(Category category);
}
