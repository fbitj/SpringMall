package com.springmall.service;

import com.springmall.bean.Admin;
import com.springmall.bean.AdminExample;
import com.springmall.bean.LoginVo;
import com.springmall.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    }
}
