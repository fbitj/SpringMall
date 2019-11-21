package com.springmall.controller.admin;
import java.io.IOException;


import com.springmall.bean.*;
import com.springmall.service.AdminService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.System;
import java.util.*;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/15 21:11
 */
@RestController
@RequestMapping("admin")
public class AdminController {

//    @RequiresPermissions(value = {"user:query","user:insert2"},logical = Logical.AND)

    @Autowired
    AdminService adminService;

    //显示管理员信息
    /**
     * request
     * admin/list?page=1&limit=20&sort=add_time&order=desc
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"total": 81,
     * 		"items": [{
     * 			"id": 165,
     * 			"username": "admin12311",
     * 			"avatar": "http://192.168.2.100:8081/wx/storage/fetch/kymo4lkgbh5xjj9e0mlh.jpg",
     * 			"roleIds": [2]
     *        }]*
     *     },
     * 	"errmsg": "成功"
     * }
     * 显示管理员信息
     * @param page      当前页数
     * @param limit     分页
     * @param username  用户名
     * @param sort      排序  按时间
     * @param order     排序  按降序
     * @return
     */
    @RequestMapping("admin/list")
//    @RequiresPermissions("admin:admin:list")
    public BaseReqVo adminList(String page, int limit, String username, String sort, String order){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = adminService.adminList(page, limit, username, sort, order);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //显示管理员权限信息
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

    //提交管理员头像   图片上传
    /**
     * 提交管理员头像
     * @param request
     * @param response
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("storage/create")
//    @RequiresPermissions("admin:storage:create")
    public BaseReqVo storage(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Storage storage = adminService.storageCreate(request, response, file);
        response.setContentType("image/jpeg");
        if(storage.getSize() == -1) {
            baseReqVo.setErrmsg("最大上传图片大小为(20MB)");
            baseReqVo.setErrno(500);
        }else {
            baseReqVo.setData(storage);
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
        }
        return baseReqVo;
    }

    //添加管理员
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
//    @RequiresPermissions("admin:admin:create")
    public BaseReqVo adminCreate(@RequestBody Admin2 admin, HttpServletRequest request) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Admin admin2 = new Admin();
        admin2.setUsername(admin.getUsername());
        int i = adminService.sameToAdminName(admin2);
        if (i != -1) {
            admin2.setPassword(admin.getPassword());
            admin2.setAvatar(admin.getAvatar());
            admin2.setRoleIds(Arrays.toString(admin.getRoleIds()));
            Map map = adminService.adminCreate(admin2, request);
            baseReqVo.setData(map);
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
            return baseReqVo;
        } else {
            baseReqVo.setErrmsg("该管理员已经存在");
            baseReqVo.setErrno(-1);
            return baseReqVo;
        }
    }

    //更改管理员信息
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
//    @RequiresPermissions("admin:admin:update")
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

    //删除管理员，并级联删除关联
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
//    @RequiresPermissions("admin:admin:delete")
    public BaseReqVo adminDelete(@RequestBody Admin2 admin2){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        adminService.adminDelete(admin2);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //显示所有角色信息
    /**
     * 显示所有角色信息
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
//    @RequiresPermissions("admin:role:list")
    public BaseReqVo roleList(int page,int limit,String name, String sort, String order){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = adminService.roleList(page, limit,name, sort, order);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //更新角色信息
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
//    @RequiresPermissions("admin:role:update")
    public BaseReqVo roleUpdate(@RequestBody Role role){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        adminService.roleUpdate(role);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //添加角色
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
//    @RequiresPermissions("admin:role:create")
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

    //授权信息
    /**
     * 授权信息
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
    @RequestMapping(value = "role/permissions",method = RequestMethod.GET)
//    @RequiresPermissions("admin:role:permissions")
    public BaseReqVo rolePermissionsUpdate(int roleId){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map1 = adminService.rolePermissions(roleId);
        baseReqVo.setData(map1);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //授权保存
    /**
     * request
     * {
     * 	"roleId": 138,
     * 	"permissions": ["admin:collect:list", "admin:feedback:list", "admin:user:list", "admin:footprint:list", "admin:history:list", "admin:address:list", "index:permission:read"]
     * }
     * response     {"errno":0,"errmsg":"成功"}
     * @return
     */
    @RequestMapping(value = "role/permissions",method = RequestMethod.POST)
//    @RequiresPermissions("admin:role:permissions")
    public BaseReqVo rolePermissionsUpdate(@RequestBody RolePermission rolePermission){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        int roleId = rolePermission.getRoleId();
        List<String> permissions1 = rolePermission.getPermissions();
        adminService.insertIntoOrUpdatePermission(roleId,permissions1);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //删除角色
    /**
     * 删除角色
     * response
     * {"errno":642,"errmsg":"当前角色存在管理员，不能删除"}
     * request
     * {
     * 	"id": 74,
     * 	"name": "li的角色",
     * 	"desc": "li的角色",
     * 	"enabled": true,
     * 	"addTime": "2019-10-03 20:55:44",
     * 	"updateTime": "2019-10-03 20:55:44",
     * 	"deleted": false
     * }
     * @return
     */
    @RequestMapping("role/delete")
//    @RequiresPermissions("admin:role:delete")
    public BaseReqVo roleDelete(@RequestBody Role role){
        BaseReqVo baseReqVo = new BaseReqVo();
        int i = adminService.roleDelete(role);
        if(i == 1) {
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
            return baseReqVo;
        }else{
            baseReqVo.setErrmsg("当前角色存在管理员，不能删除");
            baseReqVo.setErrno(-1);
            return baseReqVo;
        }
    }

    //登陆日志
    /**
     * 登陆日志
     * request
     * admin/log/list?page=1&limit=20&sort=add_time&order=desc
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"total": 5056,
     * 		"items": [{
     * 			"id": 5056,
     * 			"admin": "admin123",
     * 			"ip": "192.168.4.7",
     * 			"type": 1,
     * 			"action": "登录",
     * 			"status": true,
     * 			"result": "",
     * 			"comment": "",
     * 			"addTime": "2019-11-17 05:34:32",
     * 			"updateTime": "2019-11-17 05:34:32",
     * 			"deleted": false
     *        }]*
     *   },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("log/list")
//    @RequiresPermissions("admin:log:list")
    public BaseReqVo adminLog(int page, int limit ,String name, String sort, String order){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = adminService.logList(page, limit, name, sort, order);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //对象存储列表
    /**
     * 对象存储
     * request
     * admin/storage/list?page=1&limit=20&key=&name=&sort=add_time&order=desc
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"total": 1772,
     * 		"items": [{
     * 			"id": 1885,
     * 			"key": "96u55jj1u3rs8oh6w8fk.jpg",
     * 			"name": "bubule.jpg",
     * 			"type": "image/jpeg",
     * 			"size": 106700,
     * 			"url": "http://192.168.2.100:8081/wx/storage/fetch/96u55jj1u3rs8oh6w8fk.jpg",
     * 			"addTime": "2019-11-17 05:52:22",
     * 			"updateTime": "2019-11-17 05:52:22",
     * 			"deleted": false
     *                }]* 	},
     * 	"errmsg": "成功"
     * }
     * @param page
     * @param limit
     * @param key
     * @param name
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping("storage/list")
//    @RequiresPermissions("admin:storage:list")
    public BaseReqVo storageList(int page, int limit ,String key, String name, String sort, String order){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = adminService.storageList(page, limit, key, name, sort, order);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //修改图片名字
    /**
     * 修改图片信息
     * request
     * {
     * 	"id": 1894,
     * 	"key": "oplirid7yhjc3efyayi7.jpg",
     * 	"name": "gggh.jpg",
     * 	"type": "image/jpeg",
     * 	"size": 15639,
     * 	"url": "http://192.168.2.100:8081/wx/storage/fetch/oplirid7yhjc3efyayi7.jpg",
     * 	"addTime": "2019-11-17 06:10:01",
     * 	"updateTime": "2019-11-17 06:10:01",
     * 	"deleted": false
     * }
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 1894,
     * 		"key": "oplirid7yhjc3efyayi7.jpg",
     * 		"name": "gggh.jpg",
     * 		"type": "image/jpeg",
     * 		"size": 15639,
     * 		"url": "http://192.168.2.100:8081/wx/storage/fetch/oplirid7yhjc3efyayi7.jpg",
     * 		"addTime": "2019-11-17 06:10:01",
     * 		"updateTime": "2019-11-17 06:48:06",
     * 		"deleted": false
     *        },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("storage/update")
//    @RequiresPermissions("admin:storage:update")
    public BaseReqVo storageUpdate(@RequestBody Storage storage){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Storage storage1 = adminService.storageUpdate(storage);
        baseReqVo.setData(storage1);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //删除图片信息
    /**
     * 删除图片信息
     * request
     * {
     * 	"id": 1876,
     * 	"key": "85wsq9hrx79zh1wdnlu5.png",
     * 	"name": "head-login-img.png",
     * 	"type": "image/png",
     * 	"size": 2297,
     * 	"url": "http://192.168.2.100:8081/wx/storage/fetch/85wsq9hrx79zh1wdnlu5.png",
     * 	"addTime": "2019-11-17 05:28:15",
     * 	"updateTime": "2019-11-17 05:28:15",
     * 	"deleted": false
     * }
     * response
     * {"errno":0,"errmsg":"成功"}
     * @return
     */
    @RequestMapping("storage/delete")
//    @RequiresPermissions("admin:storage:delete")
    public BaseReqVo storageDelete(@RequestBody Storage storage){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        int i = adminService.storageDelete(storage);
        if(i == 1) {
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
            return baseReqVo;
        }else {
            baseReqVo.setErrmsg("网络异常，请重试");
            baseReqVo.setErrno(-1);
            return baseReqVo;
        }
    }
}
