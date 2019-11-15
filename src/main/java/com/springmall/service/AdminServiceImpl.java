package com.springmall.service;

import com.springmall.bean.Admin;
import com.springmall.bean.AdminExample;
<<<<<<< HEAD
import com.springmall.bean.LoginVo;
=======
>>>>>>> 2cf51b272762e8935427014aa95bf36ab05295d2
import com.springmall.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

<<<<<<< HEAD
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    /**
     * 用户登陆
     * @param loginVo
     * @return 0:用户名密码错误；1:登陆成功；-1:登陆异常
     */
    @Override
    public List<Admin> login(LoginVo loginVo) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(loginVo.getUsername()).andPasswordEqualTo(loginVo.getPassword());
        return adminMapper.selectByExample(adminExample);
=======
/**
 * Created by fwj on 2019-11-15.
 */
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminMapper adminMapper;
    @Override
    public int login(Admin admin) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(admin.getUsername()).andPasswordEqualTo(admin.getPassword());
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins.size() == 0) return 0;
        return 1;
>>>>>>> 2cf51b272762e8935427014aa95bf36ab05295d2
    }
}
