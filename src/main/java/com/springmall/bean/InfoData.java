package com.springmall.bean;

import lombok.Data;

import java.util.List;

<<<<<<< HEAD
@Data
public class InfoData {
    String avatar;
    String name;
    List<String> perms;
    List<String> roles;
=======
/**
 * Created by fwj on 2019-11-15.
 */

@Data
public class InfoData {
    private List<String> roles;

    private String name;

    private List<String> perms;

    private String avatar;

>>>>>>> 2cf51b272762e8935427014aa95bf36ab05295d2
}
