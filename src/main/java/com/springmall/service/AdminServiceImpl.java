package com.springmall.service;
import java.util.Date;


import com.springmall.bean.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.springmall.mapper.AdminMapper;
import com.springmall.mapper.LogMapper;
import com.springmall.mapper.RoleMapper;
import com.springmall.mapper.StorageMapper;
import com.springmall.utils.PasswordUtil;
import com.springmall.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/16 15:35
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    StorageMapper storageMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    LogMapper logMapper;


    @Override
    public int login(Admin admin) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(admin.getUsername())
                .andPasswordEqualTo(/*PasswordUtil.string2Stringint(*/admin.getPassword()/*)*/);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins.size() == 0) return 0;
        return 1;
    }

    @Override
    public Storage storageCreate(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws IOException {
        Storage storage = new Storage();
        if(file.getSize() > 1048576){
            storage.setSize(-1);
            return storage;
        }
        storage.setId(null);
        UUID uuid = UUID.randomUUID();
        StringBuffer requestURL = new StringBuffer();
        requestURL.append("http://localhost:8080/wx/storage/fetch/");
        String filename = file.getOriginalFilename();
        String type = filename.substring(filename.indexOf("."),filename.length());
        String s = uuid.toString();
        s = s.replaceAll("-","") + type;           //拼接key值
        //本地创建文件夹保存
        File files = new File("C:/SpringMallImage/" + s);
        if(!files.exists()){
            files.mkdirs();
        }
        file.transferTo(files);
        /*String str = "H:/MySpring/GithubMall/SpringMall/src/main/resources/static/wx/storage/fetch/" + s;
        file.transferTo(new File(str));*/
        response.setContentType("image/jpeg");

        requestURL.append(s);
        storage.setKey(s);
        storage.setName(filename);
        storage.setType("image/jpeg");
        storage.setSize((int)file.getSize());
        storage.setUrl(requestURL.toString());
        Date date = new Date();
        storage.setAddTime(date);
        storage.setUpdateTime(date);
        //将数据插入数据库中
        storageMapper.insert(storage);
        return storage;
    }

    @Override
    public List roleOption() {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdIsNotNull();
        List<Role> roles = roleMapper.selectByExample(roleExample);
        List list = new LinkedList();
        for (Role role : roles) {
            HashMap<Object, Object> map = new HashMap<>();
            map.put("value",role.getId());
            map.put("label",role.getName());
            list.add(map);
        }
        return list;
    }
/*
@Override
    public List roleOption() {
        List<Integer> integerList = new ArrayList<>();
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andIdIsNotNull();
        List<Admin> list = adminMapper.selectByExample(adminExample);
        for (Admin admin : list) {
            String roleIds = admin.getRoleIds();
            if(roleIds.length() > 3) {  //拥有多个控制权限
                String[] split = roleIds.substring(1, roleIds.lastIndexOf("]")).split(",");
                for (String s : split) {
                    integerList.add(Integer.parseInt(s.trim()));
                }
            }else if(roleIds.length() == 2){
                integerList.add(null);
            }else {
                String substring = roleIds.substring(1, roleIds.lastIndexOf("]"));
                integerList.add((Integer) Integer.parseInt(substring));
            }
        }
        List list1 = new LinkedList();
        for (Integer integer : integerList) {
            HashMap<String, Object> map = new HashMap<>();
            String s = roleMapper.selectById(integer);
            map.put("label",s);
            map.put("value",integer);
            list1.add(map);
        }
        return list1;
    }
*/

    @Override
    public Map adminList(String page, int limit, String username, String sort, String order) {
        HashMap<String, Object> map = new HashMap<>();
        AdminExample adminExample = new AdminExample();
        if(!StringUtil.isEmpty(username)) {
            adminExample.createCriteria().andIdIsNotNull().andUsernameLike("%" + username + "%");
        }else{
            adminExample.createCriteria().andIdIsNotNull();
        }
        adminMapper.selectByExample(adminExample);
        long count = adminMapper.countByExample(adminExample);
        map.put("total",(int)count);
        String s1 = sort + " " + order;
        PageHelper.startPage(Integer.parseInt(page),limit,s1);
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
            if(roleIds.equals("[]")){
                map1.put("roleIds",new ArrayList<>());
            }else {
                String[] split = roleIds.substring(1, roleIds.lastIndexOf("]")).split(",");
                List list2 = new ArrayList();
                for (String s : split) {
                    list2.add(Integer.parseInt(s.trim()));
                }
                map1.put("roleIds", list2);
            }
            list.add(map1);
        }
        map.put("items",list);
        return map;
    }

    @Override
    public Map adminCreate(Admin admin, HttpServletRequest request) {
        HashMap<Object, Object> map = new HashMap<>();
        admin.setId(null);
        admin.setUsername(admin.getUsername());
        //密码的简单hash散列加密
        admin.setPassword(PasswordUtil.string2Stringint(admin.getPassword()));
        admin.setAvatar(admin.getAvatar());
        Date addTime = new Date();
        admin.setAddTime(addTime);
        admin.setUpdateTime(addTime);
        admin.setDeleted(admin.getDeleted());
        admin.setRoleIds(admin.getRoleIds());
        adminMapper.insert(admin);

        map.put("id",admin.getId());
        map.put("username",admin.getUsername());
        map.put("password",admin.getPassword());
        map.put("avatar",admin.getAvatar());
        map.put("addTime",admin.getAddTime());
        map.put("updateTime",admin.getUpdateTime());
        String[] arr = admin.getRoleIds().substring(1,admin.getRoleIds().length() - 1).split(",");
        ArrayList arrayList = new ArrayList();
        for (String s : arr) {
            arrayList.add(Integer.parseInt(s.trim()));
        }
        map.put("roleIds",arrayList);
        return map;
    }

    @Override
    public int sameToAdminName(Admin admin) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(admin.getUsername());
        List<Admin> list = adminMapper.selectByExample(adminExample);
        if(list.size() > 0){
            return -1;
        }
        return 1;
    }

    @Override
    public Admin adminUpdate(Admin admin) {
        admin.setId(admin.getId());
        admin.setUsername(admin.getUsername());
        admin.setPassword(PasswordUtil.string2Stringint(admin.getPassword()));
        Admin admin1 = adminMapper.selectByPrimaryKey(admin.getId());
        //如果更新图片，图片名字肯定有变化
        //级联删除数据库中图片，本地图片库中图片
        StorageExample storageExample1 = new StorageExample();
        storageExample1.createCriteria().andUrlEqualTo(admin1.getAvatar());
        List<Storage> storages = storageMapper.selectByExample(storageExample1);
        if(storages != null){
            for (Storage storage : storages) {
                storageDelete(storage);
            }
        }

        admin.setUpdateTime(new Date());
        admin.setRoleIds(admin.getRoleIds());
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andIdEqualTo(admin.getId());
        adminMapper.updateByExampleDetail(admin,adminExample);
        return admin;
    }

    @Override
    public int adminDelete(Admin2 admin2) {
        //删除管理员账号
        adminMapper.deleteByPrimaryKey(admin2.getId());
        //级联删除管理员的上传图片信息
        //删除本地图片库的对应图片
        StorageExample storageExample = new StorageExample();
        storageExample.createCriteria().andUrlEqualTo(admin2.getAvatar());
        List<Storage> storages = storageMapper.selectByExample(storageExample);
        if(storages.size() > 0){
            for (Storage storage : storages) {
                storageDelete(storage);
            }
        }
        return 1;
    }

    @Override
    public Map roleList(int page, int limit,String name, String sort, String order) {
        HashMap<Object, Object> map = new HashMap<>();
        RoleExample roleExample = new RoleExample();
        if(!StringUtil.isEmpty(name)) {
            roleExample.createCriteria().andIdIsNotNull().andNameLike("%" + name + "%");
        }else{
            roleExample.createCriteria().andIdIsNotNull();
        }
        long count = roleMapper.countByExample(roleExample);
        map.put("total",(int)count);

        String s1 = sort + " " + order;
        PageHelper.startPage(page,limit,s1);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        long total1 = rolePageInfo.getTotal();

        List list = new LinkedList();
        for (Role role : roles) {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("name",role.getName());
            map1.put("id",role.getId());
            map1.put("desc",role.getDesc());
            map1.put("enabled",role.getEnabled());
            map1.put("addTime",role.getAddTime());
            map1.put("updateTime",role.getUpdateTime());
            map1.put("deleted",role.getDeleted());
            list.add(map1);
        }
        map.put("items",list);
        return map;
    }

    @Override
    public int roleUpdate(Role role) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdEqualTo(role.getId());
        Role2 role2 = new Role2();
        role2.setName(role.getName());
        role2.setDesc(role.getDesc());
        if(role.getEnabled() != null) {
            if (role.getEnabled()) {
                role2.setEnabled(1);//启用    true
            } else {
                role2.setEnabled(0);//不启用   false
            }
        }
        Date date = new Date();
        role2.setUpdateTime(date);
        if(role.getDeleted() != null) {
            if (role.getDeleted()) {
                role2.setDeleted(0);//删除    true
            } else {
                role2.setDeleted(1);//不删除   false
            }
        }
        roleMapper.updateByExampleDetail(role2,roleExample);
        return 1;
    }

    @Override
    public Role roleCreate(Role role) {
        role.setId(null);
        role.setName(role.getName());
        role.setDesc(role.getDesc());
        Date date = new Date();
        role.setUpdateTime(date);
        role.setAddTime(date);
        roleMapper.insert(role);
        return role;
    }

    @Override
    public Map rolePermissions(int roleId) {
        HashMap<Object, Object> map = new HashMap<>();

        map.put("systemPermissions","");

        map.put("assignedPermissions","");
        return map;
    }

    @Override
    public int sameToRoleName(Role role) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andNameEqualTo(role.getName());
        List<Role> roles = roleMapper.selectByExample(roleExample);
        if(roles.size() > 0){
            return -1;
        }
        return 1;
    }

    @Override
    public int roleDelete(Role role) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andRoleIdsLessThan("[]");
        List<Admin> list = adminMapper.selectByExample(adminExample);
        for (Admin admin : list) {
            String[] array = admin.getRoleIds().substring(1,admin.getRoleIds().length() - 1).split(",");
            for (String s : array) {
                if(Integer.parseInt(s.trim()) == role.getId()){
                    return -1;
                }
            }
        }
        roleMapper.deleteByPrimaryKey(role.getId());
        return 1;
    }

    @Override
    public Map logList(int page, int limit, String name, String sort, String order) {
        HashMap<Object, Object> map = new HashMap<>();
        LogExample logExample = new LogExample();
        if(!StringUtil.isEmpty(name)) {
            logExample.createCriteria().andIdIsNotNull().andAdminLike("%" + name + "%");
        }else{
            logExample.createCriteria().andIdIsNotNull();
        }
        long count = logMapper.countByExample(logExample);
        String s1 = sort + " " + order;
        map.put("total",(int)count);

        PageHelper.startPage(page,limit,s1);
        List list = new LinkedList();
        List<Log> logs = logMapper.selectByExample(logExample);
        PageInfo<Log> logPageInfo = new PageInfo<>(logs);

        for (Log log : logs) {
            list.add(log);
        }
        map.put("items",list);
        return map;
    }

    @Override
    public Map storageList(int page, int limit, String key, String name, String sort, String order) {
        HashMap<Object, Object> map = new HashMap<>();
        StorageExample storageExample = new StorageExample();
        if(StringUtil.isEmpty(name) && StringUtil.isEmpty(key)){
            storageExample.createCriteria().andIdIsNotNull();
        }
        if(!StringUtil.isEmpty(name) && !StringUtil.isEmpty(key)) {
            storageExample.createCriteria().andNameLike("%" + name + "%").andKeyLike("%" + key + "%");
        }else if(!StringUtil.isEmpty(name) && StringUtil.isEmpty(key)) {
            storageExample.createCriteria().andNameLike("%" + name + "%");
        }else if(StringUtil.isEmpty(name) && !StringUtil.isEmpty(key)){
            storageExample.createCriteria().andKeyLike("%" + key + "%");
        }
        long count = storageMapper.countByExample(storageExample);
        String s1 = sort + " " + order;
        map.put("total",(int)count);

        PageHelper.startPage(page,limit,s1);
        List<Storage> storageList = storageMapper.selectByExample(storageExample);
        PageInfo<Storage> storagePageInfo = new PageInfo<>(storageList);

        List list = new LinkedList();
        for (Storage storage : storageList) {
            list.add(storage);
        }
        map.put("items",list);
        return map;
    }

    @Override
    public Storage storageUpdate(Storage storage) {
        StorageExample storageExample = new StorageExample();
        storageExample.createCriteria().andIdEqualTo(storage.getId());
        Storage2 storage2 = new Storage2();
        storage2.setKey(storage.getKey());
        storage2.setName(storage.getName());
        storage2.setType(storage.getType());
        storage2.setSize(storage.getSize());
        storage2.setUrl(storage.getUrl());
        Date updateTime = new Date();
        storage2.setUpdateTime(updateTime);
        storage.setUpdateTime(updateTime);

        if(storage.getDeleted() != null) {
            if (storage.getDeleted()) {
                storage2.setDeleted(1); //true  删除
            } else {
                storage2.setDeleted(0); //false 不删除
            }
        }
        storageMapper.updateByExampleDetail(storage2,storageExample);
        return storage;
    }

    @Override
    public int storageDelete(Storage storage) {
        storageMapper.deleteByPrimaryKey(storage.getId());
        File file = new File("C:/SpringMallImage/" + storage.getKey());
        if(file.isFile()){
            file.delete();
        }
        return 1;
    }
}
