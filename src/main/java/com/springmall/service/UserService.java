package com.springmall.service;

import com.springmall.bean.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    List<User> queryUserList(String username,String mobile);

    int userLogin(User user);

    int register(HashMap<String, String> userInfoMap);

    int isUserExist(String username);

    int isMobileExist(String mobile);

    int resetPassword(String password, String mobile);

    void recordUserLoginInfo(String ip);
}
