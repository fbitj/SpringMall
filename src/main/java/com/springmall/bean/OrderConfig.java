package com.springmall.bean;

public class OrderConfig {

    public OrderConfig(Integer id, String litemall_order_comment, String litemall_order_unpaid, String litemall_order_unconfirm) {
        this.id = id;
        this.litemall_order_comment = litemall_order_comment;
        this.litemall_order_unpaid = litemall_order_unpaid;
        this.litemall_order_unconfirm = litemall_order_unconfirm;
    }

    private Integer id;

    private String litemall_order_comment;

    private String litemall_order_unpaid;

    private String litemall_order_unconfirm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLitemall_order_comment() {
        return litemall_order_comment;
    }

    public void setLitemall_order_comment(String litemall_order_comment) {
        this.litemall_order_comment = litemall_order_comment == null ? null : litemall_order_comment.trim();
    }

    public String getLitemall_order_unpaid() {
        return litemall_order_unpaid;
    }

    public void setLitemall_order_unpaid(String litemall_order_unpaid) {
        this.litemall_order_unpaid = litemall_order_unpaid == null ? null : litemall_order_unpaid.trim();
    }

    public String getLitemall_order_unconfirm() {
        return litemall_order_unconfirm;
    }

    public void setLitemall_order_unconfirm(String litemall_order_unconfirm) {
        this.litemall_order_unconfirm = litemall_order_unconfirm == null ? null : litemall_order_unconfirm.trim();
    }
}
