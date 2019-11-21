package com.springmall.mapper;

import com.springmall.bean.Coupon;
import com.springmall.bean.CouponExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CouponMapper {
    long countByExample(CouponExample example);

    int deleteByExample(CouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<Coupon> selectByExample(CouponExample example);

    Coupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    int deleteById(@Param("id") Integer id, @Param("time") Date time);

    List<Coupon> selectAllCoupon();

}