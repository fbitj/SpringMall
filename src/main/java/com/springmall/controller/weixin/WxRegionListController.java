package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Region;
import com.springmall.bean.RegionProvince;
import com.springmall.service.RegionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wx")
public class WxRegionListController {
    @Autowired
    RegionService regionService;
    @RequestMapping("region/list")
    public BaseReqVo<List<Region>> queryRegionList(@Param("pid")int pid){
        BaseReqVo<List<Region>> listBaseReqVo = new BaseReqVo<>();
        List<Region> regionList=regionService.queryRegionList(pid);
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(regionList);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }
}
