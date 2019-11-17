package com.springmall.utils;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/15 23:18
 */
public class StringUtil {

    public static boolean isEmpty(String str){
        if("".equals(str) || str == null){
            return true;
        }
        return false;
    }
}
