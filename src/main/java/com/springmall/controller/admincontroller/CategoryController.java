package com.springmall.controller.admincontroller;

import com.springmall.bean.BaseRespVo;
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
    public BaseRespVo listCategory() {
        BaseRespVo BaseRespVo = new BaseRespVo();
        List<CategoryResp> categories = categoryService.queryCategory();
        BaseRespVo.setData(categories);
        BaseRespVo.setErrmsg("成功");
        BaseRespVo.setErrno(0);
        return BaseRespVo;
    }

    @RequestMapping({"l1"})
    public BaseRespVo l1() {
        BaseRespVo BaseRespVo = new BaseRespVo();
        List<CategoryL1> categoryL1s = categoryService.ValueAndLabel();
        BaseRespVo.setData(categoryL1s);
        BaseRespVo.setErrmsg("成功");
        BaseRespVo.setErrno(0);
        return BaseRespVo;
    }

    /**
     * 删除商品
     * @param categoryResp
     * @return
     */
   @RequestMapping("delete")
    public BaseRespVo deleteCategory(@RequestBody CategoryResp categoryResp) {
       BaseRespVo BaseRespVo = new BaseRespVo();
       int deleteCategory = categoryService.deleteCategory(categoryResp);
       if(deleteCategory != 0) {
           BaseRespVo.setErrno(0);
           BaseRespVo.setErrmsg("成功");
       }
       return BaseRespVo;
   }

    /**
     * 添加商品
     * @param category
     * @return
     */
   @RequestMapping("create")
    public BaseRespVo createCategory(@RequestBody Category category) {
       BaseRespVo BaseRespVo = new BaseRespVo();
       int newCategory = categoryService.addCategory(category);
        if (newCategory == 1) {
            BaseRespVo.setErrno(0);
            Category selectCategory = categoryService.selectCategory(category);
            BaseRespVo.setData(selectCategory);
            BaseRespVo.setErrmsg("成功");
        }
       return BaseRespVo;
   }
   @RequestMapping("update")
    public BaseRespVo updateCategory(@RequestBody Category category) {
       BaseRespVo BaseRespVo = new BaseRespVo();
       int updateCategory = categoryService.updateCategory(category);
       if(updateCategory == 1) {
           BaseRespVo.setErrno(0);
           BaseRespVo.setErrmsg("成功");
       }
       return BaseRespVo;
   }

}
