package com.springmall.controller.admincontroller;

import com.springmall.bean.BaseRespVo;
import com.springmall.bean.Brand;
import com.springmall.service.BrandService;
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
    public BaseRespVo brandList(Integer page, Integer limit, Integer id, String name) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        //Map<String, Object> map = new HashMap<>();
        Map<String, Object> map = null;
        if (id !=null || name != null) {
             map = brandService.queryBrands(page, limit, id, name);
        } else {
             map = brandService.queryBrands(page, limit);
        }
            BaseRespVo.setData(map);
            BaseRespVo.setErrno(0);
            BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }
    //添加
    @RequestMapping("create")
    public BaseRespVo createBrand(@RequestBody Brand brand) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        Brand newBrand = brandService.addBrand(brand);
        if(newBrand != null) {
            BaseRespVo.setData(newBrand);
            BaseRespVo.setErrno(0);
            BaseRespVo.setErrmsg("成功");
        }
        return BaseRespVo;
    }
    //编辑
    @RequestMapping("update")
    public BaseRespVo updateBrand(@RequestBody Brand brand) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        Brand updateBrand = brandService.updateBrand(brand);
        if(updateBrand != null) {
            BaseRespVo.setData(updateBrand);
            BaseRespVo.setErrno(0);
            BaseRespVo.setErrmsg("成功");
        }
        return BaseRespVo;
    }
    //删除
    @RequestMapping("delete")
    public BaseRespVo deleteBrand(@RequestBody Brand brand) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        int deleteBrand = brandService.deleteBrand(brand);
        if(deleteBrand ==1) {
            BaseRespVo.setErrno(0);
            BaseRespVo.setErrmsg("成功");
        }
        return BaseRespVo;
    }
    //查询
/*    @RequestMapping("list")
    public BaseRespVo queryBrand(Integer page, Integer limit, Integer id, String name) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        Map<String, Object> map = brandService.queryBrands(page, limit, id, name);
        BaseRespVo.setData(map);
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }*/
}
