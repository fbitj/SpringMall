package com.springmall.service;

import com.springmall.bean.User;

import java.util.List;

public interface UserService {
    List<User> queryUserList(String username,String mobile);
}
