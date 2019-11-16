package com.springmall.bean;

public class MallConfig {

    public MallConfig(Integer id, String litemall_mall_name, String litemall_mall_address, String litemall_mall_phone, String litemall_mall_qq) {
        this.id = id;
        this.litemall_mall_name = litemall_mall_name;
        this.litemall_mall_address = litemall_mall_address;
        this.litemall_mall_phone = litemall_mall_phone;
        this.litemall_mall_qq = litemall_mall_qq;
    }

    private Integer id;

    private String litemall_mall_name;

    private String litemall_mall_address;

    private String litemall_mall_phone;

    private String litemall_mall_qq;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLitemall_mall_name() {
        return litemall_mall_name;
    }

    public void setLitemall_mall_name(String litemall_mall_name) {
        this.litemall_mall_name = litemall_mall_name == null ? null : litemall_mall_name.trim();
    }

    public String getLitemall_mall_address() {
        return litemall_mall_address;
    }

    public void setLitemall_mall_address(String litemall_mall_address) {
        this.litemall_mall_address = litemall_mall_address == null ? null : litemall_mall_address.trim();
    }

    public String getLitemall_mall_phone() {
        return litemall_mall_phone;
    }

    public void setLitemall_mall_phone(String litemall_mall_phone) {
        this.litemall_mall_phone = litemall_mall_phone == null ? null : litemall_mall_phone.trim();
    }

    public String getLitemall_mall_qq() {
        return litemall_mall_qq;
    }

    public void setLitemall_mall_qq(String litemall_mall_qq) {
        this.litemall_mall_qq = litemall_mall_qq == null ? null : litemall_mall_qq.trim();
    }
}
