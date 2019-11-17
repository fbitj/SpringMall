package com.springmall.service;

import com.springmall.bean.*;
import com.springmall.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * list接口
     * 显示全部商品信息。一级类目里嵌套二级类目
     * @return
     */
    @Override
    public List<CategoryResp> queryCategory() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andLevelEqualTo("L1");
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        ArrayList<CategoryResp> categoryResps = new ArrayList<>();
        for (Category category : categories) {
            List<CategoryChildren> children = queryCategoryChildren(category);
            CategoryResp categoryResp = new CategoryResp();
            categoryResp.setId(category.getId());
            categoryResp.setName(category.getName());
            categoryResp .setKeywords(category.getKeywords());
            categoryResp.setDesc(category.getDesc());
            categoryResp.setIconUrl(category.getIconUrl());
            categoryResp.setPicUrl(category.getPicUrl());
            categoryResp.setLevel(category.getLevel());
            categoryResp.setChildren(children);
            categoryResps.add(categoryResp);
        }
        return categoryResps;
    }



    /**
     * 根据(pid==id)查询二级类目，嵌套在一级类目里
     * id、pid均为一级类目的id
     * @param category
     * @return
     */
    public List<CategoryChildren> queryCategoryChildren(Category category) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidEqualTo(category.getId()).andLevelEqualTo("L2");
        List<Category> children = categoryMapper.selectByExample(categoryExample);
        ArrayList<CategoryChildren> categoryChildren = new ArrayList<>();
        for (Category child : children) {
            CategoryChildren categoryChild = new CategoryChildren();
            categoryChild.setId(child.getId());
            categoryChild.setName(child.getName());
            categoryChild.setKeywords(child.getKeywords());
            categoryChild.setDesc(child.getDesc());
            categoryChild.setIconUrl(child.getIconUrl());
            categoryChild.setPicUrl(child.getPicUrl());
            categoryChild.setLevel(child.getLevel());
            categoryChildren.add(categoryChild);
        }
        return categoryChildren;
    }

    /**
     * L1接口
     * 以value和label封装一级类目id和name
     * @return
     */
    @Override
    public List<CategoryL1> ValueAndLabel() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andLevelEqualTo("L1");
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        ArrayList<CategoryL1> categoryL1s = new ArrayList<>();
        for (Category category : categories) {
            CategoryL1 categoryL1 = new CategoryL1();
            categoryL1.setValue(category.getId());
            categoryL1.setLabel(category.getName());
            categoryL1s.add(categoryL1);
        }
        return categoryL1s;
    }

    /**
     * 根据商品id删除商品
     * 如果level==L1 删除其本身，将其下所有商品deleteed属性标记为1
     * 如果level==L2，删除其本身
     * @param categoryResp
     * @return
     */
    @Override
    public int deleteCategory(CategoryResp categoryResp) {
        CategoryExample categoryExample = new CategoryExample();
        String level = categoryResp.getLevel();
       /* if("L1".equals(level)) {
            categoryExample.createCriteria().andPidEqualTo(categoryResp.getId()).andLevelEqualTo("L2");
            int i = categoryMapper.deleteByExample(categoryExample);
        }
       int i = 0;
       if("L1".equals(level)) {
           Category category = new Category();
           category.setDeleted(true);
           category.setPid(categoryResp.getId());
            i = categoryMapper.updateByPrimaryKeySelective(category);
       }
        int delete = 0;
       if(i != 0 ) {
           categoryExample.createCriteria().andIdEqualTo(categoryResp.getId());
           delete = categoryMapper.deleteByExample(categoryExample);
       }*/
        categoryExample.createCriteria().andIdEqualTo(categoryResp.getId());
        int delete = categoryMapper.deleteByExample(categoryExample);
        return delete;
    }

    /**
     * 添加商品
     * @param category
     * @return
     */
    @Override
    public int addCategory(Category category) {
        Date date = new Date();
        category.setAddTime(date);
        category.setUpdateTime(date);
        int i = categoryMapper.insertSelective(category);
        return i;
    }

    /**
     * 根据条件查询一个category
     * @param category
     * @return
     */
    @Override
    public Category selectCategory(Category category) {
        Category select = categoryMapper.selectByPrimaryKey(category.getId());
        return select;
    }

    /**
     * 编辑商品信息
     * @param category
     * @return
     */
    @Override
    public int updateCategory(Category category) {
        Date date = new Date();
        category.setUpdateTime(date);
        int update = categoryMapper.updateByPrimaryKeySelective(category);
        return update;
    }
}
