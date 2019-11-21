package com.springmall.utils;

import java.util.UUID;

public class RandomUtil {
    public static String randomCode() {
        String s = UUID.randomUUID().toString();
        return s.toUpperCase().substring(0, 8);
    }

    public static String randomVericode() {
        double random = Math.random();
        int intRandom = (int)(random * 10000) + 1;
        return String.valueOf(intRandom);
    }
}
