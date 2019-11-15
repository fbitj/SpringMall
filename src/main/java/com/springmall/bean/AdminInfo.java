package com.springmall.bean;

import lombok.Data;

import java.util.List;

@Data
public class AdminInfo {
    private String avatar;
    private String name;
    private List<String> perms;
    private List<String> roles;
}
