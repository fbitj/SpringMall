package com.springmall.service;

import com.springmall.bean.*;
import com.springmall.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    /**
     * list接口
     * 显示全部商品信息。一级类目里嵌套二级类目
     * @return
     */
    @Override
    public List<CategoryResp> queryCategory() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andLevelEqualTo("L1").andDeletedEqualTo(false);
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        ArrayList<CategoryResp> categoryResps = new ArrayList<>();
        for (Category category : categories) {
            List<CategoryChildren> children = queryCategoryChildren(category);
            CategoryResp categoryResp = new CategoryResp();
            categoryResp.setId(category.getId());
            categoryResp.setName(category.getName());
            categoryResp.setKeywords(category.getKeywords());
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
        categoryExample.createCriteria().andPidEqualTo(category.getId()).andLevelEqualTo("L2").andDeletedEqualTo(false);
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
     * 根据商品id删除商品（逻辑删除，将deleteed属性标记为1）
     * 如果level==L1 先删除其下所有的二级商品，再删除
     * 如果level==L2，直接删除
     * @param categoryResp
     * @return
     */
    @Override
    public int deleteCategory(CategoryResp categoryResp) {
        CategoryExample categoryExample = new CategoryExample();
        String level = categoryResp.getLevel();
        if("L1".equals(level) && categoryResp.getChildren() != null) {
            List<CategoryChildren> children = categoryResp.getChildren();
            for (CategoryChildren child : children) {
                Category category = new Category();
                category.setId(child.getId());
                category.setDeleted(true);
                Date date = new Date();
                category.setUpdateTime(date);
                int deleteChild = categoryMapper.updateByPrimaryKeySelective(category);
            }
        }
        Category category = new Category();
        category.setId(categoryResp.getId());
        category.setDeleted(true);
        Date date = new Date();
        category.setUpdateTime(date);
        int delete = categoryMapper.updateByPrimaryKeySelective(category);
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
      // int delete = categoryMapper.deleteByExample(categoryExample);
      //  return delete;
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

    /**
     * 根据id查询该类以及父类和兄弟类的信息
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> queryCategoryById(Integer id) {
        //查询当前类的信息
        Category current = categoryMapper.selectByPrimaryKey(id);
        Integer pid = current.getPid();

        //查询与该类同属一个父类的类目信息
        CategoryExample example = new CategoryExample();
        example.createCriteria().andPidEqualTo(pid).andDeletedEqualTo(false);
        List<Category> categories = categoryMapper.selectByExample(example);

        //查询父类信息
        Category parent = categoryMapper.selectByPrimaryKey(pid);

        //封装信息
        Map<String,Object> map = new HashMap<>();
        map.put("currentCategory", current);
        map.put("brotherCategory", categories);
        map.put("parentCategory", parent);
        return map;
    }

    /**
     * 查询二级类目
     * @return
     */
    @Override
    public List<Category> queryCategoryByL2(List categoryId) {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andLevelEqualTo("L2");
        if (categoryId != null && categoryId.size() > 0) {
            criteria.andIdIn(categoryId);
        }
        return categoryMapper.selectByExample(example);
    }

    @Override
    public List<Category> getChannelCatagory() {
        return categoryMapper.selectChannelCatagory();
    }

    @Override
    public List<Category> getCategoryByLevel(String level) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andLevelEqualTo(level);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        return categoryList;
    }

    @Override
    public List<Category> getSubCategoryByPid(Integer id) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidEqualTo(id);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        return categoryList;
    }

    @Override
    public Category getCategoryById(Integer id) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIdEqualTo(id);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        if (categoryList.size() == 0) {
            return null;
        }
        return categoryList.get(0);
    }


}
