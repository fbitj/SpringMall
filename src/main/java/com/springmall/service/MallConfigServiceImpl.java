package com.springmall.service;

import com.springmall.bean.MallConfig;
import com.springmall.bean.MallConfigExample;
import com.springmall.mapper.MallConfigMapper;
import com.springmall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MallConfigServiceImpl implements MallConfigService {

    @Autowired
    MallConfigMapper mallConfigMapper;

    @Override
    public MallConfig queryMallConfig () {
        MallConfigExample mallConfigExample = new MallConfigExample();
        return mallConfigMapper.selectByExample(mallConfigExample).get(0);
    }

    @Override
    public int updateMallConfig (MallConfig  mallConfig ) {
        if (StringUtils.isEmpty(mallConfig.getLitemall_mall_name()) ||
            StringUtils.isEmpty(mallConfig.getLitemall_mall_address()) ||
            StringUtils.isEmpty(mallConfig.getLitemall_mall_phone()) ||
            StringUtils.isEmpty(mallConfig.getLitemall_mall_qq())
        ){    //修改条件不完整
            return 2;
        }
        MallConfig updateMallConfig = new MallConfig(
                1,
                mallConfig.getLitemall_mall_name(),
                mallConfig.getLitemall_mall_address(),
                mallConfig.getLitemall_mall_phone(),
                mallConfig.getLitemall_mall_qq()
        );
        MallConfigExample mallConfigExample = new MallConfigExample();
        int result = 0;
        if(mallConfigMapper.selectByExample(mallConfigExample).size()==0){    //表中无商场配置相关数据，插入一条配置信息。
            result = mallConfigMapper.insertSelective(updateMallConfig);
            return result;
        }
        result = mallConfigMapper.updateByPrimaryKeySelective(updateMallConfig);    //表中有商场配置相关数据，更新该条配置信息。
        return result;
    }
}
