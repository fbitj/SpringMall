package com.springmall.utils;

public class StringUtils {

    public static boolean isEmpty(String string){
        if(string == null || "".equals(string.trim())){
           return true;
        }
        return false;
    }
}
