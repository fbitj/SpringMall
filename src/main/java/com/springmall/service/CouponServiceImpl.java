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

import java.math.BigDecimal;
import java.util.*;

@Service
public class CouponServiceImpl implements CouponService{

    @Autowired
    CouponMapper couponMapper;
    @Autowired
    Coupon_userMapper coupon_userMapper;
    @Autowired
    CartService cartService;

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
     * 根据条件查询所有用户优惠券并分页
     * @param request
     * @return
     */
    @Override
    public DataForPage<Coupon_user> showListUserByPage(PageRequest request) {
        PageHelper.startPage(request.getPage(), request.getLimit());

        Coupon_userExample example = new Coupon_userExample();
        Coupon_userExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(request.getCouponId())) {
            criteria.andCouponIdEqualTo(Integer.parseInt(request.getCouponId()));
        }
        if (request.getStatus() != null) {
            criteria.andStatusEqualTo(request.getStatus());
        }
        criteria.andDeletedEqualTo(false);
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

    /**
     * 分页显示可用的通用券信息
     * @param page
     * @param size
     * @return 当前页可用优惠券信息列表与列表长度的封装Map
     */
    @Override
    public HashMap<String, Object> queryCouponListByPage(String page, String size) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(size));
        CouponExample couponExample = new CouponExample();
        //优惠券状态需为正常可用，优惠券赠送类型需为通用券，过滤逻辑删除。
        couponExample.createCriteria().andStatusEqualTo((short) 0).andTypeEqualTo((short) 0).andDeletedEqualTo(false);
        List<Coupon> couponList = couponMapper.selectByExample(couponExample);
        PageInfo<Coupon> pageInfo = new PageInfo<>(couponList);
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("data",couponList);
        dataMap.put("count",pageInfo.getTotal());
        return dataMap;
    }

    /**
     * 领取优惠券
     * @param couponId
     * @param userId
     * @return
     * -1表示服务器异常
     * 0表示领取失败
     * 1表示领取成功
     * 2表示当前登录用户已领取该券数已达到限制，不可再领取。
     * 3表示该券已被领完
     */
    @Override
    public int receiveCouponByCouponId(int couponId, int userId) {
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        return exchangeCouponJudge(coupon,userId);
    }

    /**
     * 兑换优惠券
     * @param code
     * @param userId
     * @return
     * -1表示服务器异常
     * 0表示兑换码不正确
     * 1表示兑换成功
     * 2表示当前登录用户已兑换该券数已达到限制，不可再兑换。
     * 3表示该券已被兑换完
     * 4表示该券已失效，不可再兑换。
     */
    @Override
    public int exchangeCoupon(String code, int userId) {
        //查询是否存在该优惠券兑换码
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria().andCodeEqualTo(code).andDeletedEqualTo(false);
        int isExist = (int) couponMapper.countByExample(couponExample);
        if (isExist<=0){    //兑换码不存在
            return 0;
        }
        criteria.andStatusEqualTo((short) 0);
        List<Coupon> couponList = couponMapper.selectByExample(couponExample);
        if(couponList.size()<=0){    //该券状态为已过期或已下架，不可再兑换。
            return 4;
        }
        Coupon coupon = couponList.get(0);
        return exchangeCouponJudge(coupon,userId);
    }

    /**
     * 优惠券领取和兑换的辅助方法
     * @param coupon 优惠券对象
     * @param userId 当前登录用户的id
     * @return 状态码
     */
    private int exchangeCouponJudge(Coupon coupon, int userId){
        //若coupon表中的total常量不为0，则该券限量。
        //需根据coupon_user表查询该优惠券已被领取的数量，需过滤逻辑删除，计算该券余量。
        if(coupon.getTotal()!=0){    //该优惠券限量发放
            Coupon_userExample couponUserExample = new Coupon_userExample();
            couponUserExample.createCriteria().andCouponIdEqualTo(coupon.getId()).andDeletedEqualTo(false);
            int sendCount = (int) coupon_userMapper.countByExample(couponUserExample);    //该券已被领取数量
            int remainCount=coupon.getTotal()-sendCount;    //该券余量
            if (remainCount<=0){    //该券已被领完
                return 3;
            }
        }
        //若coupon表中的limit常量不为0，则单个用户可领取该券数限量。
        //需根据coupon_user表查询该用户已领取该券的数量，需过滤逻辑删除，判断当前是否可领取。
        if (coupon.getLimit()!=0){    //单个用户可领取该券数限量
            Coupon_userExample couponUserExample = new Coupon_userExample();
            couponUserExample.createCriteria().andCouponIdEqualTo(coupon.getId()).andUserIdEqualTo(userId).andDeletedEqualTo(false);
            int receiveCount = (int) coupon_userMapper.countByExample(couponUserExample);    //当前登录用户已领取该券的数量
            if(receiveCount>=coupon.getLimit()){    //当前登录用户已领取该券数已达到限制，不可再领取。
                return 2;
            }
        }
        //在coupon_user表中插入数据
        //若coupon表中的time_type为0，则将当前时间设为start_time，加上有效天数days作为end_time。
        Coupon_user couponUser = null;
        if(coupon.getTimeType()==0){
            Date startTime = new Date();
            Date end_time = new Date(startTime.getTime() + 1000*60*60*24*coupon.getDays());
            couponUser = new Coupon_user(userId,coupon.getId(), (short) 0,startTime,end_time,false);
        }
        //若coupon表中的time_type为1，则取出coupon表中的start_time和end_time赋给coupon_user表。
        if (coupon.getTimeType()==1){
            couponUser = new Coupon_user(userId,coupon.getId(), (short) 0,coupon.getStartTime(),coupon.getEndTime(),false);
        }
        int result = coupon_userMapper.insertSelective(couponUser);
        return result;
    }

    /**
     * 分页显示当前登录用户指定状态的优惠券列表
     * @param status
     * @param page
     * @param size
     * @param userId
     * @return 当前页当前登录用户指定状态的优惠券列表与列表长度的封装Map
     */
    @Override
    public HashMap<String, Object> queryMyCouponListByStatusAndPage(String status, String page, String size, int userId) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(size));
        Coupon_userExample couponUserExample = new Coupon_userExample();
        couponUserExample.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(Short.valueOf(status)).andDeletedEqualTo(false);    //需过滤逻辑删除
        List<Coupon_user> couponUserList = coupon_userMapper.selectByExample(couponUserExample);
        List<Coupon> couponList = new ArrayList<>();
        for (Coupon_user couponUser : couponUserList) {
            Coupon coupon = couponMapper.selectByPrimaryKey(couponUser.getCouponId());
            if(!coupon.getDeleted()){
                couponList.add(coupon);
            }
        }
        PageInfo<Coupon> pageInfo = new PageInfo<>(couponList);
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("data",couponList);
        dataMap.put("count",pageInfo.getTotal());
        return dataMap;
    }

    /**
     * 获取当前订单全部可用优惠券列表
     * @param userId
     * @return 当前订单可用的优惠券列表
     */
    @Override
    public List<Coupon> queryOrderCouponList(int userId) {
        Coupon_userExample couponUserExample = new Coupon_userExample();
        //获取该用户所有已拥有的未使用状态的优惠券，过滤逻辑删除。
        couponUserExample.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo((short) 0).andDeletedEqualTo(false);    //过滤逻辑删除
        List<Coupon_user> couponUserList = coupon_userMapper.selectByExample(couponUserExample);
        List<Integer> couponIdList = new ArrayList<>();
        for (Coupon_user couponUser : couponUserList) {
            couponIdList.add(couponUser.getCouponId());
        }
        /*
        现在先假设商品总价为goodsTotalPrice
        */
        BigDecimal goodsTotalPrice = new BigDecimal(500);
        //根据wx/cart/checkout接口的service层方法，获取下单前信息确认的商品总价goodsTotalPrice。
        /*
        BigDecimal goodsTotalPrice = cartService.checkout().get("goodsTotalPrice");
        */
        CouponExample couponExample = new CouponExample();
        //优惠券还需满足：最低可用消费金额小于等于下单前信息确认的商品总价、状态为可使用，需过滤逻辑删除。
        couponExample.createCriteria().andStatusEqualTo((short) 0).andMinLessThanOrEqualTo(goodsTotalPrice).andDeletedEqualTo(false);
        List<Coupon> couponList = couponMapper.selectByExample(couponExample);
        return couponList;
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return couponMapper.selectAllCoupon();
    }
}
