package com.springmall.server;

import com.springmall.mapper.DashboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/15 17:51
 */
@Service
public class DashBoardImpl implements DashBoard {

    @Autowired
    DashboardMapper dashboardMapper;

    @Override
    public int getGoodsTotal() {
        return dashboardMapper.getGoodsTotal();
    }

    @Override
    public int getOrderTotal() {
        return dashboardMapper.getOrderTotal();
    }

    @Override
    public int getProductTotal() {
        return dashboardMapper.getProductTotal();
    }

    @Override
    public int getUserTotal() {
        return dashboardMapper.getUserTotal();
    }
}
