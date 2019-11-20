package com.springmall.controller.admincontroller;

import com.springmall.bean.BaseRespVo;
import com.springmall.bean.RegionProvince;
import com.springmall.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/region")
public class RegionController {
    @Autowired
    RegionService regionService;
    @RequestMapping("list")
    public BaseRespVo regionList() {
        BaseRespVo BaseRespVo = new BaseRespVo();
        List<RegionProvince> regionResps = regionService.queryRegion();
        if(regionResps != null) {
            BaseRespVo.setErrno(0);
            BaseRespVo.setData(regionResps);
            BaseRespVo.setErrmsg("成功");
        }
        return BaseRespVo;
    }
}
