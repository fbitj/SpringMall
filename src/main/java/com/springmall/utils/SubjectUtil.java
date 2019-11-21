package com.springmall.utils;

import com.springmall.bean.User;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/20 15:33
 */
@Data
public class SubjectUtil {

    public static User getUser(){
        Subject subject = SecurityUtils.getSubject();
        //获取sessionId
//        Serializable id1 = subject.getSession().getId();
        User user = (User) subject.getPrincipal();
        return user;
    }

}
