package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.springmall.bean.Footprint;
import com.springmall.bean.FootprintExample;
import com.springmall.mapper.FootprintMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * 查询用户足迹
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List queryUserFootPrintByPage(Integer userId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Map> list = footprintMapper.queryUserFootPrintByPage(userId);
        for (Map map : list) {
            Date addTime = (Date) map.get("addTime");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(addTime);
            map.put("addTime", format);
        }
        return list;
    }

    /**
     * 添加用户足迹
     * @param goodsId
     * @param userId
     * @return
     */
    @Override
    public int addUserFoot(Integer goodsId, Integer userId) {
        Footprint footprint = new Footprint();
        footprint.setGoodsId(goodsId);
        footprint.setUserId(userId);
        return footprintMapper.insertSelective(footprint);
    }
}
