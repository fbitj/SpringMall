package com.springmall.controller.admincontroller;

import com.springmall.bean.Ad;
import com.springmall.bean.PageRequest;
import com.springmall.bean.BaseRespVo;
import com.springmall.bean.DataForPage;
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
    public BaseRespVo advertiseList(PageRequest adRequest) {
        DataForPage<Ad> advertises = advertiseService.totalAdvertise(adRequest);

        BaseRespVo reqVo = ResultUtil.success(advertises);

        /*BaseRespVo<Map<String,Object>> reqVo = new BaseRespVo<>();
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
    public BaseRespVo<Ad> update(@RequestBody Ad advertise) {
        Ad result = advertiseService.update(advertise);

        BaseRespVo reqVo = ResultUtil.success(result);
        return reqVo;
    }

    /**
     * 新增广告
     * @param advertise
     * @return
     */
    @RequestMapping("create")
    public BaseRespVo<Ad> createAd(@RequestBody Ad advertise) {
        Ad result = advertiseService.create(advertise);
        BaseRespVo reqVo = ResultUtil.success(result);
        return reqVo;
    }

    /**
     * 删除某条广告信息
     * @param advertise
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo deleteById(@RequestBody Ad advertise) {
        //暂定仅删除单表中的数据
        advertiseService.delete(advertise.getId());
        return ResultUtil.success(null);
    }


}
