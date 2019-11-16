package com.springmall.service;

import com.springmall.bean.Admin;
import com.springmall.bean.AdminExample;
import com.springmall.bean.LoginVo;
import com.springmall.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    }
}
