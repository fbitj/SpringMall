package com.springmall.mapper;

import com.springmall.bean.User_formid;
import com.springmall.bean.User_formidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface User_formidMapper {
    long countByExample(User_formidExample example);

    int deleteByExample(User_formidExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User_formid record);

    int insertSelective(User_formid record);

    List<User_formid> selectByExample(User_formidExample example);

    User_formid selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User_formid record, @Param("example") User_formidExample example);

    int updateByExample(@Param("record") User_formid record, @Param("example") User_formidExample example);

    int updateByPrimaryKeySelective(User_formid record);

    int updateByPrimaryKey(User_formid record);
}