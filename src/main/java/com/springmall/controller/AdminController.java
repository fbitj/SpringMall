package com.springmall.controller;
import java.io.IOException;


import com.springmall.bean.*;
import com.springmall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    AdminService adminService;

    /**
     * 显示管理员信息
     * @param page      当前页数
     * @param limit     分页
     * @param username  用户名
     * @param sort      排序  按时间
     * @param order     排序  按降序
     * @return
     */
    @RequestMapping("admin/list")
    public BaseReqVo adminList(String page, int limit, String username, String sort, String order){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = adminService.adminList(page, limit, username, sort, order);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    /**
     * 显示管理员权限信息
     * {response
     * 	"errno": 0,
     * 	"data": [{
     * 		"value": 1,
     * 		"label": "超级管理员"
     *        }, {
     * 		"value": 2,
     * 		"label": "商场管理员"
     *    }],
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("role/options")
    public BaseReqVo option(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        List list = adminService.roleOption();
        baseReqVo.setData(list);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 提交管理员头像
     * @param request
     * @param response
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("storage/create")
    public BaseReqVo storage(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Storage storage = adminService.storageCreate(request, response, file);
        response.setContentType("image/jpeg");
        baseReqVo.setData(storage);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    /**
     * 添加管理员
     * request
     * {
     * 	"username": "ad123hj",
     * 	"password": "sf456k",
     * 	"avatar": "http://192.168.2.100:8081/wx/storage/fetch/41ozs8puxs51izaiujzt.jpg",
     * 	"roleIds": [1]
     * }
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 129,
     * 		"username": "ad123hj",
     * 		"password": "$2a$10$dHR.y4qMcioPTaRtBVTYAeRI9ioplI0.Tjyok30SaMPFFQ0fY4s3W",
     * 		"avatar": "http://192.168.2.100:8081/wx/storage/fetch/41ozs8puxs51izaiujzt.jpg",
     * 		"addTime": "2019-11-16 03:15:42",
     * 		"updateTime": "2019-11-16 03:15:42",
     * 		"roleIds": [1]
     *        },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("admin/create")
    @ResponseBody
    public BaseReqVo adminCreate(@RequestBody Admin2 admin, HttpServletRequest request) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Admin admin2 = new Admin();
        admin2.setUsername(admin.getUsername());
        int i = adminService.sameToAdminName(admin2);
        if (i != -1) {
            admin2.setPassword(admin.getPassword());
            admin2.setAvatar(admin.getAvatar());
            admin2.setRoleIds(Arrays.toString(admin.getRoleIds()));
            Admin admin1 = adminService.adminCreate(admin2, request);
            baseReqVo.setData(admin1);
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
            return baseReqVo;
        } else {
            baseReqVo.setErrmsg("该管理员已经存在");
            baseReqVo.setErrno(-1);
            return baseReqVo;
        }
    }

    /**
     * 更改管理员信息
     * request
     * {
     * 	"id": 128,
     * 	"username": "beat123",
     * 	"avatar": "http://192.168.2.100:8081/wx/storage/fetch/2fj94ugila1f03s4vndj.jpg",
     * 	"roleIds": [2, 3],
     * 	"password": "heihei23"
     * }
     * {    response
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 128,
     * 		"username": "beat123",
     * 		"password": "$2a$10$9Tt.UKN6o5ykqytcBUnnc.x4pWhQftYaFsu0zPcYz07TCQOAs3n8C",
     * 		"avatar": "http://192.168.2.100:8081/wx/storage/fetch/2fj94ugila1f03s4vndj.jpg",
     * 		"updateTime": "2019-11-16 04:19:15",
     * 		"roleIds": [2, 3]
     *        },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("admin/update")
    public BaseReqVo updateAdmin(@RequestBody Admin2 admin){
        Admin admin2 = new Admin();
        BaseReqVo<Object> boaseReqVo = new BaseReqVo<>();
        admin2.setId(admin.getId());
        admin2.setUsername(admin.getUsername());
        admin2.setPassword(admin.getPassword());
        admin2.setAvatar(admin.getAvatar());
        admin2.setRoleIds(Arrays.toString(admin.getRoleIds()));
        admin2.setUpdateTime(admin.getUpdateTime());

        Admin admin1 = adminService.adminUpdate(admin2);
        boaseReqVo.setData(admin1);
        boaseReqVo.setErrmsg("成功");
        boaseReqVo.setErrno(0);
        return boaseReqVo;
    }

    /**
     *  request
     *  {
     * 	"id": 129,
     * 	"username": "ad123hj",
     * 	"avatar": "http://192.168.2.100:8081/wx/storage/fetch/41ozs8puxs51izaiujzt.jpg",
     * 	"roleIds": [1]
     * }
     *  response  删除管理员信息
     * {"errno":0,"errmsg":"成功"}
     * @return
     */
    @RequestMapping("admin/delete")
    public BaseReqVo adminDelete(@RequestBody Admin2 admin2){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        adminService.adminDelete(admin2);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    /**
     * request
     * admin/role/list?page=1&limit=20&sort=add_time&order=desc
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"total": 22,
     * 		"items": [{
     * 			"id": 100,
     * 			"name": "嗯嗯",
     * 			"desc": "你是素以",
     * 			"enabled": true,
     * 			"addTime": "2019-11-16 06:12:54",
     * 			"updateTime": "2019-11-16 06:12:54",
     * 			"deleted": false
     *                }]* 	},
     * 	"errmsg": "成功"
     * }
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping("role/list")
    public BaseReqVo roleList(int page,int limit,String name, String sort, String order){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = adminService.roleList(page, limit,name, sort, order);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    /**
     * 更新角色
     * {
     * 	"id": 100,
     * 	"name": "嗯嗯",
     * 	"desc": "你是素以还好",
     * 	"enabled": true,                是否启用
     * 	"addTime": "2019-11-16 06:12:54",
     * 	"updateTime": "2019-11-16 06:12:54",
     * 	"deleted": false                逻辑删除
     * }
     * @return
     */
    @RequestMapping("role/update")
    public BaseReqVo roleUpdate(@RequestBody Role role){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        adminService.roleUpdate(role);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    /**
     * 添加角色
     * request
     * {"name":"hasakey","desc":"死亡如风，常伴吾身"}
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 101,
     * 		"name": "hasakey",
     * 		"desc": "死亡如风，常伴吾身",
     * 		"addTime": "2019-11-16 09:22:09",
     * 		"updateTime": "2019-11-16 09:22:09"
     *        },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("role/create")
    public BaseReqVo roleCreate(@RequestBody Role role){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        int i = adminService.sameToRoleName(role);
        if(i != -1) {
            Role role1 = adminService.roleCreate(role);
            baseReqVo.setData(role1);
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
            return baseReqVo;
        }else{
            baseReqVo.setErrmsg("该角色名称已经存在");
            baseReqVo.setErrno(-1);
            return baseReqVo;
        }
    }

//

    /**
     * request          admin/role/permissions?roleId=73
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"systemPermissions": [{
     * 			"id": "用户管理",
     * 			"label": "用户管理",
     * 			"children": [{
     * 				"id": "用户收藏",
     * 				"label": "用户收藏",
     * 				"children": [{
     * 					"id": "admin:collect:list",
     * 					"label": "查询",
     * 					"api": "GET /admin/collect/list"
     *                }]*
     *           }]
     *        }],
     * 		"assignedPermissions": ["admin:config:express:updateConfigs", "admin:config:express:list"]*
     *  	},
     * 	"errmsg": "成功"
     * }
     * @param roleId
     * @return
     */
    @RequestMapping("role/permissions")
    public BaseReqVo rolePermissions(int roleId){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = adminService.rolePermissions(roleId);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }


}
