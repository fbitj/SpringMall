package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.mapper.CouponMapper;
import com.springmall.mapper.Coupon_userMapper;
import com.springmall.utils.RandomUtil;
import com.springmall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponServiceImpl implements CouponService{

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    Coupon_userMapper coupon_userMapper;

    /**
     * 查找优惠券并分页
     * @param request
     * @return
     */
    @Override
    public List<Coupon> totalCoupons(PageRequest request) {
        //分页
        PageHelper.startPage(request.getPage(), request.getLimit());

        Short type = request.getType();
        Short status = request.getStatus();

        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria();
        //判空，若不为空则添加条件
        if (!StringUtils.isEmpty(request.getName())) {
            criteria.andNameLike("%" + request.getName() + "%");
        }
        if (type != null) {
            criteria.andGoodsTypeEqualTo(type);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        criteria.andDeletedEqualTo(false);
        List<Coupon> coupons = couponMapper.selectByExample(couponExample);

        return coupons;
    }

    /**
     * 添加优惠券
     * @param coupon
     * @return
     */
    @Override
    public Coupon addCoupon(Coupon coupon) {
        Date date = new Date();
        //更新时间
        coupon.setAddTime(date);
        coupon.setUpdateTime(date);
        //判断优惠券是否为兑换码类型
        if (coupon.getType() == 2) {
            String code = RandomUtil.randomCode();
            coupon.setCode(code);
        }
        couponMapper.insertSelective(coupon);
        return coupon;
    }

    /**
     * 获取指定id的优惠券信息
     * @param id
     * @return
     */
    @Override
    public Coupon showInfo(Integer id) {
        return couponMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据条件所有用户优惠券并分页
     * @param request
     * @return
     */
    @Override
    public DataForPage<Coupon_user> showListUserByPage(PageRequest request) {
        PageHelper.startPage(request.getPage(), request.getLimit());

        Coupon_userExample example = new Coupon_userExample();
        if (!StringUtils.isEmpty(request.getCouponId())) {
            example.createCriteria().andCouponIdEqualTo(Integer.parseInt(request.getCouponId()));
        }
        if (request.getStatus() != null) {
            example.createCriteria().andStatusEqualTo(request.getStatus());
        }
        List<Coupon_user> couponUsers = coupon_userMapper.selectByExample(example);

        PageInfo<Coupon_user> pageInfo = new PageInfo(couponUsers);
        long total = pageInfo.getTotal();

        return new DataForPage(total, couponUsers);
    }

    /**
     * 更新单个优惠券信息
     * @param
     * @return
     */
    @Override
    public Coupon updateListUser(Coupon coupon) {
        //判断优惠券是否为兑换码类型
        if (coupon.getType() == 2) {
            String code = RandomUtil.randomCode();
            coupon.setCode(code);
        } else {
            coupon.setCode(null);
        }
        Date date = new Date();
        coupon.setUpdateTime(date);
        couponMapper.updateByPrimaryKey(coupon);
        return coupon;
    }

    /**
     * 删除优惠券信息
     * 同时更新优惠券使用者信息
     * @param coupon
     * @return
     */
    @Override
    @Transactional
    public int deleteCoupon(Coupon coupon) {
        //删除优惠券
        Integer id = coupon.getId();
        int i = couponMapper.deleteById(id);
        i = coupon_userMapper.deleteByCouponId(id);
        return i;
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return couponMapper.selectAllCoupon();
    }
}
