package com.springmall.service;

import com.springmall.bean.Admin;
import com.springmall.bean.LoginVo;

import java.util.List;

public interface AdminService {
    List<Admin> login(LoginVo loginVo);
}
