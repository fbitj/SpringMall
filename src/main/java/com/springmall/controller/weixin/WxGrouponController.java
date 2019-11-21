package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.GrouponDetail;
import com.springmall.bean.User;
import com.springmall.service.GrouponService;
import com.springmall.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 处理前台系统所有关于groupon模块下的请求
 * URL：/wx/groupon/list
 *      /wx/groupon/query
 *      /wx/groupon/my
 *      /wx/groupon/detail
 *      /wx/groupon/join
 */
@RestController
@RequestMapping("wx/groupon")
public class WxGrouponController {

    @Autowired
    GrouponService grouponService;

    /**
     * 分页显示团购信息
     * @param page 当前分页页码
     * @param size 每页显示条目数
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo queryGrouponListByPage(String page, String size){
        HashMap<String,Object> dataMap = grouponService.queryGrouponListByPage(page,size);
        return ResultUtil.success(dataMap);
    }

    /**
     * 根据参团角色获取登录用户的团购信息
     * @param showType 参团角色
     *                 0表示当前登录用户为团购的发起者
     *                 1表示当前登录用户为团购的参加者
     * @return
     */
    @RequestMapping("my")
    public BaseReqVo queryMyGrouponByShowType(String showType){
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        HashMap<String,Object> dataMap = grouponService.queryMyGrouponByShowType(showType,user.getId());
        return ResultUtil.success(dataMap);
    }

    /**
     * 获取当前登录用户指定编号的团购详情
     * @param grouponId groupon表的id
     * @return
     */
    @RequestMapping("detail")
    public BaseReqVo queryGrouponByGrouponId(int grouponId){
        GrouponDetail dataBean = grouponService.queryGrouponByGrouponId(grouponId);
        return ResultUtil.success(dataBean);
    }
}
