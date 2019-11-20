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
    //
    private List couponList;
    // L1的分类频道
    private List channel;

    private List grouponList;

    private List banner;

    private List brandList;

    private List hotGoodsList;

    private List topicList;

    private List floorGoodsList;




}
