package com.springmall.bean;

import lombok.Data;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/21 14:21
 */
@Data
public class Log3 {
    String action;
    int status;
    String result;

    public Log3() {
    }

    public Log3(String action, int status, String result) {
        this.action = action;
        this.status = status;
        this.result = result;
    }
}
