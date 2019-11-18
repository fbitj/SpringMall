package com.springmall.service;

import com.springmall.bean.CatAndBrandReqVo;
import com.springmall.bean.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有的分类信息并按等级归类
     * @return
     */
    List<CatAndBrandReqVo> queryAllCategoryByLevel();
}
