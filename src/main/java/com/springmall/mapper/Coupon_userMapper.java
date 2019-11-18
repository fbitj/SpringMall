package com.springmall.mapper;

import com.springmall.bean.Coupon_user;
import com.springmall.bean.Coupon_userExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Coupon_userMapper {
    long countByExample(Coupon_userExample example);

    int deleteByExample(Coupon_userExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Coupon_user record);

    int insertSelective(Coupon_user record);

    List<Coupon_user> selectByExample(Coupon_userExample example);

    Coupon_user selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Coupon_user record, @Param("example") Coupon_userExample example);

    int updateByExample(@Param("record") Coupon_user record, @Param("example") Coupon_userExample example);

    int updateByPrimaryKeySelective(Coupon_user record);

    int updateByPrimaryKey(Coupon_user record);

    int deleteByCouponId(Integer id);
}