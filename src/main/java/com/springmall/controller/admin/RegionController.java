package com.springmall.controller.admin;

import com.springmall.bean.BaseReqVo;
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
    public BaseReqVo regionList() {
        BaseReqVo baseReqVo = new BaseReqVo();
        List<RegionProvince> regionResps = regionService.queryRegion();
        if(regionResps != null) {
            baseReqVo.setErrno(0);
            baseReqVo.setData(regionResps);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
}
