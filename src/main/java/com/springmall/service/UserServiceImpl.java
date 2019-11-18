package com.springmall.service;

import com.springmall.bean.User;
import com.springmall.bean.UserExample;
import com.springmall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理——会员管理——service
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> queryUserList(String username, String mobile) {
        UserExample userExample = new UserExample();
        List<User> users = null;
        if ((username == null||"".equals(username) )&&(mobile == null||"".equals(mobile))){
            users = userMapper.selectByExample(userExample);
        } else if ((username != null|| username.length()>0)&& (mobile == null || "".equals(mobile))) {
            userExample.createCriteria().andUsernameLike("%"+username+"%");
            users = userMapper.selectByExample(userExample);
        } else if ((mobile != null || mobile.length()>0) && (username == null|| "".equals(username))){
            userExample.createCriteria().andMobileEqualTo(mobile);
            users = userMapper.selectByExample(userExample);
        } else {
            userExample.createCriteria().andUsernameLike("%"+username+"%").andMobileEqualTo(mobile);
            users = userMapper.selectByExample(userExample);
        }
        return users;
    }

}
