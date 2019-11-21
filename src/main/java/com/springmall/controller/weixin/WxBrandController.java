package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Brand;
import com.springmall.bean.PageRequest;
import com.springmall.service.BrandService;
import com.springmall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx/brand")
public class WxBrandController {

    @Autowired
    BrandService brandService;

    /**
     * 显示具体品牌信息
     * @param id
     * @return
     */
    @RequestMapping("detail")
    public BaseReqVo brandInfo(Integer id) {
        Brand brand = brandService.queryBrandById(id);
        Map<String,Brand> map = new HashMap<>();
        map.put("brand", brand);
        return ResultUtil.success(map);
    }

    /**
     * 显示所有品牌信息并分页
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo allBrand(PageRequest pageRequest) {
        Map map = brandService.queryAllBrandByPage(pageRequest);
        return ResultUtil.success(map);
    }
}
