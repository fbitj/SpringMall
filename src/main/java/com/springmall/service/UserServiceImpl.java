package com.springmall.service;

import com.springmall.bean.User;
import com.springmall.bean.UserExample;
import com.springmall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Override
    public int userLogin(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0)
            return 2;
        return 1;
    }

    @Override
    public int register(HashMap<String, String> userInfoMap) {
        return userMapper.insertUser(userInfoMap);
    }

    @Override
    public int isUserExist(String username) {
        return userMapper.selectUserCountByName(username);
    }

    @Override
    public int isMobileExist(String mobile) {
        return userMapper.selectUserCountByMobile(mobile);
    }

    // 根据手机号重置用户密码
    @Override
    public int resetPassword(String password, String mobile) {
        User user = new User();
        user.setPassword(password);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andMobileEqualTo(mobile);
        return userMapper.updateByExampleSelective(user, userExample);

    }


}
