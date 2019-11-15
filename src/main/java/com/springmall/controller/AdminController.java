package com.springmall.controller;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.mapper.AdminMapper;
import com.springmall.mapper.RoleMapper;
import com.springmall.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/15 21:11
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;

///admin/admin/list?page=1&limit=20&sort=add_time&order=desc
    @RequestMapping("admin/list")
    public BaseReqVo adminList(String page, int limit, String username, String sort, String order){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        AdminExample adminExample = new AdminExample();
//        adminExample.createCriteria().and
        if(!StringUtil.isEmpty(username)) {
            adminExample.createCriteria().andIdIsNotNull().andUsernameLike("%" + username + "%");
        }else{
            adminExample.createCriteria().andIdIsNotNull();
        }
        adminMapper.selectByExample(adminExample);
        long count = adminMapper.countByExample(adminExample);
        map.put("total",(int)count);
        PageHelper.startPage(Integer.parseInt(page),limit);
        List<Admin> list1 = adminMapper.selectByExample(adminExample);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(list1);
        long total = adminPageInfo.getTotal();

        List list = new LinkedList();
        for (Admin admin : list1) {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("id",admin.getId());
            map1.put("username",admin.getUsername());
            map1.put("avatar",admin.getAvatar());
            String roleIds = admin.getRoleIds();
            String[] split = roleIds.substring(1, roleIds.lastIndexOf("]")).split(",");
            List list2 = new ArrayList();
            for (String s : split) {
                list2.add(Integer.parseInt(s));
            }
            map1.put("roleIds",list2);
            list.add(map1);
        }
        map.put("items",list);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping("role/options")
    public BaseReqVo option(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        List<Integer> integerList = new ArrayList<>();
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andIdIsNotNull();
        List<Admin> list = adminMapper.selectByExample(adminExample);
        for (Admin admin : list) {
            String roleIds = admin.getRoleIds();
            String substring = roleIds.substring(1, roleIds.lastIndexOf("]"));
            integerList.add((Integer) Integer.parseInt(substring));
        }
        List list1 = new LinkedList();
        for (Integer integer : integerList) {
            HashMap<String, Object> map = new HashMap<>();
            String s = roleMapper.selectById(integer);
            map.put("label",s);
            map.put("value",integer);
            list1.add(map);
        }
        baseReqVo.setData(list1);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("storage/create")
    public BaseReqVo storage(String file){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();

        baseReqVo.setData("");
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

}
