package com.springmall.service;

import com.springmall.bean.CatAndBrandReqVo;
import com.springmall.bean.Category;
import com.springmall.bean.CategoryExample;
import com.springmall.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CatAndBrandReqVo> queryAllCategoryByLevel() {
        // 查询所有未删除的类目
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("`level` asc");
        categoryExample.createCriteria().andDeletedNotEqualTo(true);
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        // catAndBrandReqVos用于保存一级类目并返回
        ArrayList<CatAndBrandReqVo> catAndBrandReqVos = new ArrayList<>();
        // categoryHashMap用于保存一级类目
        HashMap<Integer, CatAndBrandReqVo> categoryHashMap = new HashMap<>();
        for (Category category : categories) {
            CatAndBrandReqVo catAndBrandReqVo = new CatAndBrandReqVo();
            if (category.getPid() == 0) {// 等于0表示是一级类目
                Integer id = category.getId();
                catAndBrandReqVo.setValue(id);
                catAndBrandReqVo.setLabel(category.getName());
                categoryHashMap.put(id, catAndBrandReqVo);// 放入到map中
                catAndBrandReqVos.add(catAndBrandReqVo);
                continue;
            }
            // 不等于0表示是二级类目
            Integer pid = category.getPid();
            catAndBrandReqVo.setValue(category.getId());
            catAndBrandReqVo.setLabel(category.getName());
            // 根据父类目id查找父类目，并将子类目放入到父类目下
            CatAndBrandReqVo parentCategory = categoryHashMap.get(pid);
            if (parentCategory != null) {
                List<CatAndBrandReqVo> children = parentCategory.getChildren();
                if (children == null) {
                    parentCategory.setChildren(new ArrayList<>());
                }
                parentCategory.getChildren().add(catAndBrandReqVo);
            }
        }

        return catAndBrandReqVos;
    }
}
