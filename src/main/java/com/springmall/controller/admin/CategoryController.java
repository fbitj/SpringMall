package com.springmall.controller.admin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Category;
import com.springmall.bean.CategoryL1;
import com.springmall.bean.CategoryResp;
import com.springmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 显示全部商品
     * @return
     */
    @RequestMapping({"list"})
    public BaseReqVo listCategory() {
        BaseReqVo baseReqVo = new BaseReqVo();
        List<CategoryResp> categories = categoryService.queryCategory();
        baseReqVo.setData(categories);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping({"l1"})
    public BaseReqVo l1() {
        BaseReqVo baseReqVo = new BaseReqVo();
        List<CategoryL1> categoryL1s = categoryService.ValueAndLabel();
        baseReqVo.setData(categoryL1s);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    /**
     * 删除商品
     * @param categoryResp
     * @return
     */
   @RequestMapping("delete")
    public BaseReqVo deleteCategory(@RequestBody CategoryResp categoryResp) {
       BaseReqVo baseReqVo = new BaseReqVo();
       int deleteCategory = categoryService.deleteCategory(categoryResp);
       if(deleteCategory != 0) {
           baseReqVo.setErrno(0);
           baseReqVo.setErrmsg("成功");
       }
       return baseReqVo;
   }

    /**
     * 添加商品
     * @param category
     * @return
     */
   @RequestMapping("create")
    public BaseReqVo createCategory(@RequestBody Category category) {
       BaseReqVo baseReqVo = new BaseReqVo();
       int newCategory = categoryService.addCategory(category);
        if (newCategory == 1) {
            baseReqVo.setErrno(0);
            Category selectCategory = categoryService.selectCategory(category);
            baseReqVo.setData(selectCategory);
            baseReqVo.setErrmsg("成功");
        }
       return baseReqVo;
   }
   @RequestMapping("update")
    public BaseReqVo updateCategory(@RequestBody Category category) {
       BaseReqVo baseReqVo = new BaseReqVo();
       int updateCategory = categoryService.updateCategory(category);
       if(updateCategory == 1) {
           baseReqVo.setErrno(0);
           baseReqVo.setErrmsg("成功");
       }
       return baseReqVo;
   }
}
