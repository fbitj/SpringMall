package com.springmall.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by fwj on 2019-11-20.
 */
@Data
public class HomePageData {
    // 新品首发
    private List newGoodsList;
    // 优惠券列表
    private List couponList;
    // L1的分类频道
    private List channel;
    // 团购列表
    private List grouponList;
    // 滚动横幅图片列表
    private List banner;
    // 品牌制造商直供
    private List brandList;

    private List hotGoodsList;
    // 专题列表
    private List topicList;

    private List floorGoodsList;




}
