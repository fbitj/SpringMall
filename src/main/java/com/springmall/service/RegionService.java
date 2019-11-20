package com.springmall.service;

import com.springmall.bean.Region;
import com.springmall.bean.RegionProvince;


import java.util.List;

public interface RegionService {
    List<RegionProvince> queryRegion();
    List<Region> queryRegionList(int pid);
    String queryRegionName(Integer id);
}
