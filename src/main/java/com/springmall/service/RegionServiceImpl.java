package com.springmall.service;

import com.springmall.bean.*;
import com.springmall.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
/**
 * 三级嵌套
 * 省里嵌套地级市/区的list
 * 地级市/区里嵌套县级市/县list
 */

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    RegionMapper regionMapper;
    @Override
    public List<RegionProvince> queryRegion() {
        //查找省
        RegionExample regionExample = new RegionExample();
        regionExample.createCriteria().andTypeEqualTo((byte) 1);
        List<Region> regions = regionMapper.selectByExample(regionExample);
        ArrayList<RegionProvince> regionProvinces = new ArrayList<>();
        for (Region region : regions) {
            List<RegionCity> regionCities = queryCity(region);
            RegionProvince regionProvince = new RegionProvince();
            regionProvince.setId(region.getId());
            regionProvince.setName(region.getName());
            regionProvince.setType(region.getType());
            regionProvince.setCode(region.getCode());
            regionProvince.setChildren(regionCities);
            regionProvinces.add(regionProvince);
        }
        return regionProvinces;
    }

    /**
     * 区域列表获取
     * @param pid
     * @return
     */
    @Override
    public List<Region> queryRegionList(int pid) {
        RegionExample regionExample = new RegionExample();
        regionExample.createCriteria().andPidEqualTo(pid);
        List<Region> regionList = regionMapper.selectByExample(regionExample);
        return regionList;
    }

    public List<RegionCity> queryCity(Region province){
        //查找地级市/区
        RegionExample regionExample = new RegionExample();
        regionExample.createCriteria().andTypeEqualTo((byte) 2).andPidEqualTo(province.getId());
        List<Region> cities = regionMapper.selectByExample(regionExample);
        ArrayList<RegionCity> citiesResp = new ArrayList<>();
        for (Region city : cities) {
            List<RegionCounty> counties = queryCounty(city);
            RegionCity cityResp = new RegionCity();
            cityResp.setId(city.getId());
            cityResp.setName(city.getName());
            cityResp.setType(city.getType());
            cityResp.setCode(city.getCode());
            cityResp.setChildren(counties);
            citiesResp.add(cityResp);
        }
        return citiesResp;
    }

    public List<RegionCounty> queryCounty(Region city){
        //查找县级市/县
        RegionExample regionExample = new RegionExample();
        regionExample.createCriteria().andTypeEqualTo((byte) 3).andPidEqualTo(city.getId());
        List<Region> counties = regionMapper.selectByExample(regionExample);
        ArrayList<RegionCounty> countyChildren = new ArrayList<>();
        for (Region county : counties) {
            RegionCounty countyChild = new RegionCounty();
            countyChild.setId(county.getId());
            countyChild.setName(county.getName());
            countyChild.setType(county.getType());
            countyChild.setCode(county.getCode());
            countyChildren.add(countyChild);
        }
        return countyChildren;
    }
}

