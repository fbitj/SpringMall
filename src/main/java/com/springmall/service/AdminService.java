package com.springmall.service;

import com.springmall.bean.Admin;
<<<<<<< HEAD
import com.springmall.bean.LoginVo;

import java.util.List;

public interface AdminService {
    List<Admin> login(LoginVo loginVo);
=======
import org.springframework.stereotype.Service;

/**
 * Created by fwj on 2019-11-15.
 */
public interface AdminService {

    int login(Admin admin);
>>>>>>> 2cf51b272762e8935427014aa95bf36ab05295d2
}
