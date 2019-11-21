package com.springmall.controller.weixin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Footprint;
import com.springmall.bean.PageRequest;
import com.springmall.bean.User;
import com.springmall.service.FootPrintService;
import com.springmall.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx/footprint")
public class WxFootPrintController {

    @Autowired
    FootPrintService footPrintService;

    /**
     * 获取用户足迹信息并分页
     * @param request
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo userFootPrint(PageRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        if (principal == null) return ResultUtil.fail(700, "请登陆后重试");
        Integer userId = principal.getId();
        List footList = footPrintService.queryUserFootPrintByPage(userId, request.getPage(), request.getSize());
        //封装数据
        PageInfo<Footprint> info = new PageInfo<>(footList);
        Map result = new HashMap();
        result.put("footprintList", footList);
        result.put("totalPages", info.getPages());
        return ResultUtil.success(result);
    }

    /**
     * 删除足迹
     * @param footprint
     * @return
     */
    @RequestMapping("delete")
    public BaseReqVo deleteFootPrint(@RequestBody Footprint footprint) {
        footprint.setDeleted(true);
        int i = footPrintService.deleteFootPrintById(footprint);
        if (i == 0) {
            return ResultUtil.fail(402, "服务器异常，请登陆后重试");
        }
        return ResultUtil.success(null);
    }
}
