package com.springmall.mapper;

import com.springmall.bean.User;
import com.springmall.bean.UserExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springmall.bean.UserStat;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // 查询用户增长的统计数据
    List<UserStat> selectUserCountOfDay();

    int insertUser(HashMap<String, String> userInfoMap);

    int selectUserCountByName(String username);

    int selectUserCountByMobile(String mobile);
}