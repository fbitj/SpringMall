package com.springmall.controller.admin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Brand;
import com.springmall.service.BrandService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/brand")
public class BrandController {
    @Autowired
    BrandService brandService;
    //显示全部品牌
    @RequestMapping("list")
    @RequiresPermissions(value = {"admin:brand:update"})
    public BaseReqVo brandList(Integer page, Integer limit, Integer id, String name) {
        BaseReqVo baseReqVo = new BaseReqVo();
        //Map<String, Object> map = new HashMap<>();
        Map<String, Object> map = null;
        if (id !=null || name != null) {
             map = brandService.queryBrands(page, limit, id, name);
        } else {
             map = brandService.queryBrands(page, limit);
        }
            baseReqVo.setData(map);
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    //添加
    @RequestMapping("create")
    @RequiresPermissions(value = {"admin:brand:create"})
    public BaseReqVo createBrand(@RequestBody Brand brand) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Brand newBrand = brandService.addBrand(brand);
        if(newBrand != null) {
            baseReqVo.setData(newBrand);
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
    //编辑
    @RequestMapping("update")
    @RequiresPermissions(value = {"admin:brand:update"})
    public BaseReqVo updateBrand(@RequestBody Brand brand) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Brand updateBrand = brandService.updateBrand(brand);
        if(updateBrand != null) {
            baseReqVo.setData(updateBrand);
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
    //删除
    @RequestMapping("delete")
    @RequiresPermissions(value = {"admin:brand:delete"})
    public BaseReqVo deleteBrand(@RequestBody Brand brand) {
        BaseReqVo baseReqVo = new BaseReqVo();
        int deleteBrand = brandService.deleteBrand(brand);
        if(deleteBrand ==1) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
    //查询
/*    @RequestMapping("list")
    public BaseReqVo queryBrand(Integer page, Integer limit, Integer id, String name) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Map<String, Object> map = brandService.queryBrands(page, limit, id, name);
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }*/
}
