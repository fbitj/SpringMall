package com.springmall.controller.admin;

import com.springmall.bean.Ad;
import com.springmall.bean.PageRequest;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.DataForPage;
import com.springmall.service.AdvertiseService;
import com.springmall.utils.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequiresPermissions(value = {"admin:ad:list"})
    public BaseReqVo advertiseList(PageRequest adRequest) {
        DataForPage<Ad> advertises = advertiseService.totalAdvertise(adRequest);

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

    /**
     * 新增广告
     * @param advertise
     * @return
     */
    @RequestMapping("create")
    public BaseReqVo<Ad> createAd(@RequestBody Ad advertise) {
        Ad result = advertiseService.create(advertise);
        BaseReqVo reqVo = ResultUtil.success(result);
        return reqVo;
    }

    /**
     * 删除某条广告信息
     * @param advertise
     * @return
     */
    @RequestMapping("delete")
    public BaseReqVo deleteById(@RequestBody Ad advertise) {
        //暂定仅删除单表中的数据
        advertiseService.delete(advertise.getId());
        return ResultUtil.success(null);
    }


}
