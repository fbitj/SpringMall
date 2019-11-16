package com.springmall.controller;

import com.springmall.bean.Ad;
import com.springmall.bean.AdRequest;
import com.springmall.bean.BaseReqVo;
import com.springmall.service.AdvertiseService;
import com.springmall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/ad")
public class AdvertiseController {

    @Autowired
    AdvertiseService advertiseService;

    /**
     * 获取所有广告信息并分页显示
     * @param adRequest
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo<Map<String,Object>> advertiseList(AdRequest adRequest) {
        Map<String,Object> advertises = advertiseService.totalAdvertise(adRequest);

        BaseReqVo reqVo = ResultUtil.success(advertises);

        /*BaseReqVo<Map<String,Object>> reqVo = new BaseReqVo<>();
        reqVo.setData(advertises);
        reqVo.setErrno(0);
        reqVo.setErrmsg("成功");*/
        return reqVo;
    }

    /**
     * 更新广告信息
     * @param advertise
     * @return
     */
    @RequestMapping("update")
    public BaseReqVo<Ad> update(@RequestBody Ad advertise) {
        Ad result = advertiseService.update(advertise);

        BaseReqVo reqVo = ResultUtil.success(result);
        return reqVo;
    }

    @RequestMapping("create")
    public BaseReqVo<Ad> createAd(@RequestBody Ad advertise) {
        Ad result = advertiseService.create(advertise);
        BaseReqVo reqVo = ResultUtil.success(result);
        return reqVo;
    }
}
