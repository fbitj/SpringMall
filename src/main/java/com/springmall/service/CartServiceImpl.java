package com.springmall.service;


import com.springmall.bean.*;
import com.springmall.bean.System;
import com.springmall.mapper.*;
import com.springmall.utils.GetUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    Goods_productMapper goods_productMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    Coupon_userMapper coupon_userMapper;
    @Autowired
    GetUserUtil getUserUtil;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    Groupon_rulesMapper grouponRulesMapper;
    @Autowired
    SystemMapper systemMapper;

    /**
     * 显示所有购物车内数据，deleted = false
     *
     * @return
     */
    @Override
    public Map<String, Object> cartTotal() {
        int userId = getUserUtil.getUserId();
        if (userId != -1) {
            CartExample cartExample = new CartExample();
            //要切记，显示的是没有删除的数据，deleted = false
            cartExample.createCriteria().andUserIdEqualTo(userId).andDeletedEqualTo(false);
            List<Cart> carts = cartMapper.selectByExample(cartExample);
            //获取goodsCountTotal
            int goodsCount = goodsCountCart();
    /*    PageInfo<Cart> cartPageInfo = new PageInfo<>(carts);
        long goodsCount = cartPageInfo.getTotal();*/
            //获取goodsAmount //商品总金额
            BigDecimal goodsAmount = new BigDecimal(0.00);
            for (Cart cart : carts) {
                BigDecimal price = cart.getPrice();
                price = price.multiply(BigDecimal.valueOf(cart.getNumber()));
                //同一件商品的总价（本质：price * number）
         /*   for (int i = 1; i < cart.getNumber() ; i++) {
                price = price.add(price);
            }*/
                goodsAmount = goodsAmount.add(price);
            }
            //获取checked = true 的goods
            CartExample checkedCartExample = new CartExample();
            checkedCartExample.createCriteria().andUserIdEqualTo(userId).andCheckedEqualTo(true).andDeletedEqualTo(false);
            List<Cart> checkedCarts = cartMapper.selectByExample(checkedCartExample);
            //获取checkedGoodsCount
            int checkedGoodsCount = checkedGoodsCountCart();
            //获取checkedGoodsAmount
            BigDecimal checkedGoodsAmount = new BigDecimal(0.00);
            for (Cart checkedCart : checkedCarts) {
                BigDecimal price = checkedCart.getPrice();
                //乘法，price x number
                price = price.multiply(BigDecimal.valueOf(checkedCart.getNumber()));
                checkedGoodsAmount = checkedGoodsAmount.add(price);
            }
            //将所有的total封装成map
            Map<String, Object> totalMap = new HashMap<>();
            totalMap.put("goodsCount", goodsCount);
            totalMap.put("checkedGoodsCount", checkedGoodsCount);
            totalMap.put("goodsAmount", goodsAmount);
            totalMap.put("checkedGoodsAmount", checkedGoodsAmount);
            //将totalMap和carts封装成map
            Map<String, Object> map = new HashMap<>();
            map.put("cartTotal", totalMap);
            map.put("cartList", carts);
            return map;
        }
        return null;
    }

    /**
     * 根据userId和productId 逻辑删除
     * 即设置：deleted = true
     *
     * @return
     */
    @Override
    public Map<String, Object> deleteCart(List<Integer> productIds) {
        int userId = getUserUtil.getUserId();
        if (userId != -1) {
            for (int productId : productIds) {
                int delete = cartMapper.logicDeleteCartByUserIdAndProductId(userId, productId);
            }
            Map<String, Object> cartTotal = cartTotal();
            return cartTotal;
        }
        return null;
    }

    /**
     * 选择或者取消选择
     * isChecked = 1(true) ,选择
     * isChecked = 0(false) ,取消选择
     *
     * @param productIds
     * @param isChecked
     * @return 购物车所有数据 cartTotal
     */
    @Override
    public Map<String, Object> checkedCart(List<Integer> productIds, boolean isChecked) {
        int userId = getUserUtil.getUserId();
        if (userId != -1) {
            for (int productId : productIds) {
                //注意逻辑删除，否则会查到逻辑删除的商品
                Cart cart = cartMapper.selectByUserIdAndProductIdAndDeleted(userId, productId, false);
                cart.setChecked(isChecked);
                int checked = cartMapper.updateByPrimaryKeySelective(cart);
              /*  CartExample cartExample = new CartExample();
                cartExample.createCriteria().andProductIdEqualTo(productId).andUserIdEqualTo(userId);
                int checked = cartMapper.updateByExampleSelective(cart, cartExample);*/
            }
            Map<String, Object> cartTotal = cartTotal();
            return cartTotal;
        }
        return null;
    }

    /**
     * 更新购物车的商品(只能改变数量)
     * 不需要userId,因为前台传了cartId(主键)
     *
     * @param cartReq
     * @return
     */
    @Override
    public int updateCart(Cart cartReq) {
        Cart cart = cartMapper.selectByPrimaryKey(cartReq.getId());
        cart.setNumber(cartReq.getNumber());
        Date date = new Date();
        cart.setUpdateTime(date);
        int update = cartMapper.updateByPrimaryKeySelective(cart);
        return update;
    }

    /**
     * 获取购物车商品件数（所有商品的总number）
     *
     * @return
     */
    @Override
    public int goodsCountCart() {
        int userId = getUserUtil.getUserId();
        int goodsCount = 0;
        if (userId != -1) {
            CartExample cartExample = new CartExample();
            cartExample.createCriteria().andDeletedEqualTo(false).andUserIdEqualTo(userId);
            List<Cart> carts = cartMapper.selectByExample(cartExample);
            for (Cart cart : carts) {
                Short goodsnumber = cart.getNumber();
                goodsCount += goodsnumber.intValue();
            }
        }
        return goodsCount;
    }

           /* CartExample cartExample = new CartExample();
            cartExample.createCriteria().andDeletedEqualTo(false).andUserIdEqualTo(userId);
            List<Cart> carts = cartMapper.selectByExample(cartExample);
            int goodsCount = 0;
            for (Cart cart : carts) {
                Short goodsnumber = cart.getNumber();
                goodsCount += goodsnumber.intValue();
            }
            return goodsCount;*/
       /* CartExample cartExample = new CartExample();
        cartExample.createCriteria().andDeletedEqualTo(false).andUserIdEqualTo(userId);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        int goodsCount = 0;
        for (Cart cart : carts) {
            Short goodsnumber = cart.getNumber();
            goodsCount += goodsnumber.intValue();
        }
        return goodsCount;*/
    //      return 0;


    /**
     * 添加商品到购物车
     * 查询goods表，获取goodsSn, goodsName,
     * 查询goods_product表， 获取productId, price, specifications, pic_url
     * 默认checked=true
     * 细节：productId存在（即为specifications(型号)相同），要调用update而不是insert
     *
     * @param cart
     * @return购物车中商品总数
     */
    @Override
    public int addCart(Cart cart) {
        //生成一条购物车单据，并添加到数据库,createCart()方法在后面
        String request = "addCart";
        Cart addCart = createCart(cart, request);
        if(addCart != null) {
            //返回购物车中商品总数
            int goodsCountCart = goodsCountCart();
            return goodsCountCart;
        }
        return 0;
    }

    /**
     * 立即购买商品，点击即会生成购物车单据，付款后会删除（不用这里实现此功能）
     * 未付款退出，依然保留在购物车
     * 表里有同类型商品，不会增加新数据，而是修改number
     * 细节：productId存在（即为specifications(型号)相同），要调用update而不是insert
     *
     * @param cart
     * @return 购物车id
     */
    @Override
    public int fastAddCart(Cart cart) {
        String request = "fastAddCart";
        Cart fastAddCart = createCart(cart, request);
        if (fastAddCart != null) {
            Integer cartId = fastAddCart.getId();
            return cartId;
        }
        //   int insert = cartMapper.insertSelective(fastAddCart);
     return 0;
    }


    /**
     * 下单前信息确认
     *
     * @param cartId
     * @param addressId
     * @param couponId
     * @param grouponRulesId
     * @return 一个map。
     * 包括checkedAddress（收货地址），checkedGoodsList(所有商品的cart数据)
     * goodsTotalPrice 无优惠的总价
     * grouponPrice团购价，grouponId = 0时，grouponPrice=0
     * actualPrice 实付价（实付费用 = order_price - integral_price（积分减免））
     * orderTotalPrice 订单总价（订单费用 = goods_price + freight_price - coupon_price）
     * couponPrice 优惠金额
     * availableCouponLength 可用优惠券的数量（根据user查询，status=0,计算总量）
     * 优惠券状态（使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；）
     * couponId 优惠券id
     * freightPrice 运费
     * addressId
     */
    @Override
    public Map<String, Object> checkOutBeforePay(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId) {
        int userId = getUserUtil.getUserId();
        if (userId != -1) {
            List<Cart> carts = null;
            if (cartId != 0) {
                //立即购买
                CartExample cartExample = new CartExample();
                cartExample.createCriteria().andIdEqualTo(cartId).andUserIdEqualTo(userId);
                carts = cartMapper.selectByExample(cartExample);
            } else {
                //购物车下单，购买的是所有checked=true的商品
                CartExample cartExamples = new CartExample();
                //永远不要忘记去掉逻辑删除的商品
                cartExamples.createCriteria().andCheckedEqualTo(true).andUserIdEqualTo(userId).andDeletedEqualTo(false);
                carts = cartMapper.selectByExample(cartExamples);
            }
            //求goodsTotalPrice
            BigDecimal goodsTotalPrice = new BigDecimal(0);
            for (Cart cart : carts) {
                BigDecimal price = cart.getPrice();
                goodsTotalPrice = goodsTotalPrice.add(price.multiply(BigDecimal.valueOf(cart.getNumber())));
            }
            //不管哪种方式，都会有一个addressId
            Address address = null;
            AddressExample addressExample = new AddressExample();
            addressExample.createCriteria().andUserIdEqualTo(userId).andDeletedEqualTo(false);
            List<Address> addresses = addressMapper.selectByExample(addressExample);
            if (addresses.size() != 0) {
              //  int mark = 0;
                for (Address addr : addresses) {
                    if (addr.getId() == addressId) {
                   //     mark = 1;
                        break;
                    }
                }
                address = addressMapper.selectByPrimaryKey(addressId);
            } else {
                addressId = 0;
            }


//        Address address = addressMapper.selectByPrimaryKey(addressId);
            //团购,根据grouponRulesId,查询折扣金额
            BigDecimal grouponPrice = new BigDecimal(0);
            if (grouponRulesId != 0) {
                Groupon_rules grouponRules = grouponRulesMapper.selectByPrimaryKey(grouponRulesId);
                grouponPrice = grouponRules.getDiscount();
                //减去团购优惠金额
                goodsTotalPrice = goodsTotalPrice.add(grouponPrice.negate());
            }

            //优惠券折扣金额
            BigDecimal couponPrice = new BigDecimal(0);
            //可用优惠券数量
            Coupon_userExample coupon_userExample = new Coupon_userExample();
            coupon_userExample.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo((short) 0).andDeletedEqualTo(false);
            List<Coupon_user> coupon_users = coupon_userMapper.selectByExample(coupon_userExample);
            int availableCouponLength = coupon_users.size();
            //couponId不为0，根据couponId去coupon表搜索优惠金额，再用couponId和userId去coupon_user表搜索可用优惠券，并计算可用优惠券数量
            //couponId==0 表示无优惠券可用，couponId==1 表示有优惠券可用
            if (couponId != 0 ) {
                //搜索优惠金额
                Coupon coupon = null;
                for (Coupon_user coupon_user : coupon_users) {
                    coupon = couponMapper.selectByPrimaryKey(coupon_user.getCouponId());
                    //比较大小，左边比右边大，返回1。左边比右边小返回-1。相等返回0。
                    if(goodsTotalPrice.compareTo(coupon.getMin()) != -1) {
                        couponId = coupon_user.getId();
                        break;
                    }
                }
//                Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
                couponPrice = coupon.getDiscount();
          /*  Coupon_userExample coupon_userExample = new Coupon_userExample();
            coupon_userExample.createCriteria().andCouponIdEqualTo(couponId).andUserIdEqualTo(userId).andStatusEqualTo((short) 0).andDeletedEqualTo(false);
            List<Coupon_user> coupon_users = coupon_userMapper.selectByExample(coupon_userExample);
             availableCouponLength = coupon_users.size();*/
            }
            //运费，基础运费8元，满88包邮
            //搜索减免运费最小金额
            System express_freight_min = systemMapper.selectByPrimaryKey(5);
            String minCost = express_freight_min.getKeyValue();
            //基础运费
            System express_freight_value = systemMapper.selectByPrimaryKey(7);
            String carriage = express_freight_value.getKeyValue();

            BigDecimal freightPrice = new BigDecimal(carriage);
            BigDecimal criterion = new BigDecimal(minCost);
            //比较大小，左边比右边大，返回1。左边比右边小返回-1。相等返回0。
            if (goodsTotalPrice.compareTo(criterion) == 1) {
                freightPrice = BigDecimal.valueOf(0);
            }
            //orderTotalPrice  订单费用 = goods_price + freight_price - coupon_price
            BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice).add(couponPrice.negate());
            //integralPrice 积分减免
            BigDecimal integralPrice = new BigDecimal(0);
            //actualPrice 实付金额 实付费用 = order_price - integral_price（积分减免）
            // integralPrice.negate()表示返回一个负数 -integralPrice
            BigDecimal actualPrice = orderTotalPrice.add(integralPrice.negate());

            //封装传给前台的数据
            Map<String, Object> map = new HashMap<>();
            map.put("grouponPrice", grouponPrice);
            map.put("grouponRulesId", grouponRulesId);
            map.put("checkedAddress", address);
            map.put("addressId", addressId);
            map.put("checkedGoodsList", carts);
            map.put("goodsTotalPrice", goodsTotalPrice);
            map.put("actualPrice", actualPrice);
            map.put("orderTotalPrice", orderTotalPrice);
            map.put("couponId", couponId);
            map.put("couponPrice", couponPrice);
            map.put("availableCouponLength", availableCouponLength);
            map.put("freightPrice", freightPrice);
            return map;
        }
        return null;
    }

    /**
     * 获取购物车里选中商品件数（所有选中商品的总number）
     *
     * @return
     */
    public int checkedGoodsCountCart() {
        int userId = getUserUtil.getUserId();
        if (userId != -1) {
            CartExample cartExample = new CartExample();
            cartExample.createCriteria().andDeletedEqualTo(false).andCheckedEqualTo(true).andUserIdEqualTo(userId);
            List<Cart> carts = cartMapper.selectByExample(cartExample);
            int checkedGoodsCount = 0;
            for (Cart cart : carts) {
                Short checkedGoodsnumber = cart.getNumber();
                checkedGoodsCount += checkedGoodsnumber.intValue();
            }
            return checkedGoodsCount;
        }
        return 0;
    }

    /**
     * 生成一条购物车数据
     * 细节：productId存在（即为specifications(型号)相同），要调用update而不是insert
     * 细节，排除逻辑删除的同款商品
     *
     * @param cart
     * @return
     */
    public Cart createCart(Cart cart, String request) {
        //已知goodsId, number, productId
        //通过session获取userId
        int userId = getUserUtil.getUserId();
        if (userId != -1) {
            //查询goods表，获取goodsSn, goodsName,
            Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoodsId());
            //查询goods_product表， 获取productId, price, specifications, pic_url
            Goods_product goods_product = goods_productMapper.selectByPrimaryKey(cart.getProductId());
            //判单购物车是否有同款，有的话update
           // boolean deleted = false;
            Cart selectCart = cartMapper.selectByUserIdAndProductIdAndDeleted(userId, cart.getProductId(), false);
            if (selectCart != null) {
                //存在同型号的商品，直接update。 number = oldNumber + newNumber
                //判断是添加购物车调用还是立即购买调用，对number的处理不同
                if ("addCart".equals(request)) {
                    //添加购物车，新旧数据合并
                    selectCart.setNumber((short) (selectCart.getNumber() + cart.getNumber()));
                    int update = cartMapper.updateByPrimaryKeySelective(selectCart);
                } else if ("fastAddCart".equals(request)) {
                    //直接购买，数据更新为新数据
                    selectCart.setNumber(cart.getNumber());
                    int update = cartMapper.updateByPrimaryKeySelective(selectCart);
                }
                return selectCart;
            } else {
                //封装数据
                cart.setUserId(userId);
                cart.setGoodsSn(goods.getGoodsSn());
                cart.setGoodsName(goods.getName());
                cart.setPrice(goods_product.getPrice());
                cart.setSpecifications(goods_product.getSpecifications());
                cart.setPicUrl(goods_product.getUrl());
                //默认checked=true (选中)
                cart.setChecked(true);
                Date date = new Date();
                cart.setAddTime(date);
                cart.setUpdateTime(date);
                int insert = cartMapper.insertSelective(cart);
                return cart;
            }
        }
        return null;
    }
}
