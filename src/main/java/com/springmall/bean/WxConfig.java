package com.springmall.bean;

public class WxConfig {

    public WxConfig(Integer id, String litemall_wx_index_new, String litemall_wx_catlog_goods, String litemall_wx_catlog_list, String litemall_wx_share, String litemall_wx_index_brand, String litemall_wx_index_hot, String litemall_wx_index_topic) {
        this.id = id;
        this.litemall_wx_index_new = litemall_wx_index_new;
        this.litemall_wx_catlog_goods = litemall_wx_catlog_goods;
        this.litemall_wx_catlog_list = litemall_wx_catlog_list;
        this.litemall_wx_share = litemall_wx_share;
        this.litemall_wx_index_brand = litemall_wx_index_brand;
        this.litemall_wx_index_hot = litemall_wx_index_hot;
        this.litemall_wx_index_topic = litemall_wx_index_topic;
    }

    private Integer id;

    private String litemall_wx_index_new;

    private String litemall_wx_catlog_goods;

    private String litemall_wx_catlog_list;

    private String litemall_wx_share;

    private String litemall_wx_index_brand;

    private String litemall_wx_index_hot;

    private String litemall_wx_index_topic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLitemall_wx_index_new() {
        return litemall_wx_index_new;
    }

    public void setLitemall_wx_index_new(String litemall_wx_index_new) {
        this.litemall_wx_index_new = litemall_wx_index_new == null ? null : litemall_wx_index_new.trim();
    }

    public String getLitemall_wx_catlog_goods() {
        return litemall_wx_catlog_goods;
    }

    public void setLitemall_wx_catlog_goods(String litemall_wx_catlog_goods) {
        this.litemall_wx_catlog_goods = litemall_wx_catlog_goods == null ? null : litemall_wx_catlog_goods.trim();
    }

    public String getLitemall_wx_catlog_list() {
        return litemall_wx_catlog_list;
    }

    public void setLitemall_wx_catlog_list(String litemall_wx_catlog_list) {
        this.litemall_wx_catlog_list = litemall_wx_catlog_list == null ? null : litemall_wx_catlog_list.trim();
    }

    public String getLitemall_wx_share() {
        return litemall_wx_share;
    }

    public void setLitemall_wx_share(String litemall_wx_share) {
        this.litemall_wx_share = litemall_wx_share == null ? null : litemall_wx_share.trim();
    }

    public String getLitemall_wx_index_brand() {
        return litemall_wx_index_brand;
    }

    public void setLitemall_wx_index_brand(String litemall_wx_index_brand) {
        this.litemall_wx_index_brand = litemall_wx_index_brand == null ? null : litemall_wx_index_brand.trim();
    }

    public String getLitemall_wx_index_hot() {
        return litemall_wx_index_hot;
    }

    public void setLitemall_wx_index_hot(String litemall_wx_index_hot) {
        this.litemall_wx_index_hot = litemall_wx_index_hot == null ? null : litemall_wx_index_hot.trim();
    }

    public String getLitemall_wx_index_topic() {
        return litemall_wx_index_topic;
    }

    public void setLitemall_wx_index_topic(String litemall_wx_index_topic) {
        this.litemall_wx_index_topic = litemall_wx_index_topic == null ? null : litemall_wx_index_topic.trim();
    }
}
