package com.springmall.service;

import com.springmall.bean.Footprint;
import com.springmall.bean.FootprintExample;
import com.springmall.mapper.FootprintMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理——会员足迹——service层
 */
@Service
public class FootPrintServiceImpl implements FootPrintService {
    @Autowired
    FootprintMapper footprintMapper;

    @Override
    public List<Footprint> queryFootPrintList(Integer userId, Integer goodsId) {
        List<Footprint> footprintList = null;
        FootprintExample footprintExample = new FootprintExample();
        if (userId == null && goodsId == null) {
            footprintList = footprintMapper.selectByExample(footprintExample);
        } else if (userId != null && goodsId == null) {
            footprintExample.createCriteria().andUserIdEqualTo(userId);
            footprintList = footprintMapper.selectByExample(footprintExample);
        } else if (goodsId != null && userId == null) {
            footprintExample.createCriteria().andGoodsIdEqualTo(goodsId);
            footprintList = footprintMapper.selectByExample(footprintExample);
        }else{
            footprintExample.createCriteria().andGoodsIdEqualTo(goodsId).andUserIdEqualTo(userId);
            footprintList=footprintMapper.selectByExample(footprintExample);
        }
        return footprintList;
    }
}
