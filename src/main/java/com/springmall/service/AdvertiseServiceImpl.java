package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.AdExample;
import com.springmall.bean.AdRequest;
import com.springmall.bean.Ad;
import com.springmall.mapper.AdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

    @Autowired
    AdMapper advertiseMapper;

    /**
     * 获取所有广告信息并分页
     * @param adRequest
     * @return
     */
    @Override
    public Map<String,Object> totalAdvertise(AdRequest adRequest) {
        //分页
        PageHelper.startPage(adRequest.getPage(), adRequest.getLimit());


        List<Ad> advertises = advertiseMapper.selectAllWithParm(adRequest);

        PageInfo<Ad> pageInfo = new PageInfo<>(advertises);
        long total = pageInfo.getTotal();

        Map<String,Object> result = new HashMap<>();
        result.put("total", total);
        result.put("items", advertises);
        return result;
    }

    /**
     * 更新广告信息
     * @param advertise
     * @return
     */
    @Override
    public Ad update(Ad advertise) {
        //添加更新时间
        Date date = new Date();
        advertise.setUpdateTime(date);

        AdExample example = new AdExample();
        example.createCriteria().andIdEqualTo(advertise.getId());
        advertiseMapper.updateByExampleSelective(advertise, example);
        return advertise;
    }

    /**
     * 新增广告信息
     * @param advertise
     * @return
     */
    @Override
    public Ad create(Ad advertise) {
        Date date = new Date();
        advertise.setUpdateTime(date);
        advertise.setAddTime(date);
        advertiseMapper.insertSelective(advertise);
        return advertise;
    }

    /**
     * 删除ad表中的某条数据
     * @param id
     */
    @Override
    public void delete(Integer id) {
        advertiseMapper.deleteByPrimaryKey(id);
    }
}
