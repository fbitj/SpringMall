package com.springmall.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.Address;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.User;
import com.springmall.service.AddressService;
import com.springmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;

    /**
     * 用户管理——会员管理——controller层
     * @return
     */
    @RequestMapping("user/list")
    @ResponseBody
    public BaseReqVo<Map<String, Object>> getUserList(Integer page, Integer limit, String sort, String order, String username, String mobile) {
        BaseReqVo<Map<String, Object>> mapBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(page,limit);
        List<User> users = userService.queryUserList(username, mobile);
        //實現了分頁
        PageInfo<User> userPageInfo=new PageInfo<>(users);
        long total = userPageInfo.getTotal();
        map.put("total", total);
        map.put("items", users);
        mapBaseReqVo.setErrno(0);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");

        return mapBaseReqVo;
    }

    /**
     * 用户管理——收货地址——controller层
     * @return
     */
    @RequestMapping("address/list")
    @ResponseBody
    public BaseReqVo<Map<String, Object>> getAddressList(Integer page, Integer limit, String name, Integer userId, String sort, String order) {
        BaseReqVo<Map<String, Object>> mapBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(page, limit);
        List<Address> addresses = addressService.queryAddressList(userId, name);
        //分頁
        PageInfo<Address> addressPageInfo=new PageInfo<>(addresses);
        long total = addressPageInfo.getTotal();
        mapBaseReqVo.setErrno(0);
        map.put("total",total);
        map.put("items", addresses);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        return mapBaseReqVo;
    }
}
