package com.springmall.bean;

public class ExpressConfig {

    public ExpressConfig(Integer id, String litemall_express_freight_min, String litemall_express_freight_value) {
        this.id = id;
        this.litemall_express_freight_min = litemall_express_freight_min;
        this.litemall_express_freight_value = litemall_express_freight_value;
    }

    private Integer id;

    private String litemall_express_freight_min;

    private String litemall_express_freight_value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLitemall_express_freight_min() {
        return litemall_express_freight_min;
    }

    public void setLitemall_express_freight_min(String litemall_express_freight_min) {
        this.litemall_express_freight_min = litemall_express_freight_min == null ? null : litemall_express_freight_min.trim();
    }

    public String getLitemall_express_freight_value() {
        return litemall_express_freight_value;
    }

    public void setLitemall_express_freight_value(String litemall_express_freight_value) {
        this.litemall_express_freight_value = litemall_express_freight_value == null ? null : litemall_express_freight_value.trim();
    }
}
