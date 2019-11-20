package com.springmall.bean;

import lombok.Data;

/**
 * 用于接收前端评论后发送的数据
 */
@Data
public class OrderGoodsCommentReqVo {
    /**
     * 订单id
     */
    Integer orderGoodsId;
    String content;
    short star;
    boolean hasPicture;
    String[] picUrls;
}
