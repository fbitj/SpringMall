package com.springmall.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by fwj on 2019-11-15.
 */

@Data
public class InfoData {
    private List<String> roles;

    private String name;

    private List<String> perms;

    private String avatar;

}
