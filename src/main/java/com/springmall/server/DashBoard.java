package com.springmall.server;

import org.springframework.stereotype.Service;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/15 17:50
 */

public interface DashBoard {

    int getGoodsTotal();

    int getOrderTotal();

    int getProductTotal();

    int getUserTotal();
}
