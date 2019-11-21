package com.springmall.service;

import com.springmall.bean.Comment;
import com.springmall.bean.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> queryUserList(String username,String mobile);

    int userLogin(User user);

    Map collectAddOrDelete(int type, int value, int id);

    Map collectList(int type,int page,int size, int id);

    Map userIndex(int id);

    Map commentList(int valueId, int type, int size, int page, int showType);

    Map commentCount(int valueId ,int type);

    Map topicList(int pge, int size);

    List topicRelated(int id);

    Map topicDetail(int id);

    Comment commentPost(Comment comment);

    int register(HashMap<String, String> userInfoMap);

    int isUserExist(String username);

    int isMobileExist(String mobile);

    int resetPassword(String password, String mobile);

    void recordUserLoginInfo(String ip);
}
