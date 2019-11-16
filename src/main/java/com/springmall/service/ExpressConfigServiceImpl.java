package com.springmall.service;

import com.springmall.bean.ExpressConfig;
import com.springmall.bean.ExpressConfigExample;
import com.springmall.mapper.ExpressConfigMapper;
import com.springmall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpressConfigServiceImpl implements ExpressConfigService {

    @Autowired
    ExpressConfigMapper expressConfigMapper;

    /**
     * 获取运费配置信息
     * @return 现运费配置信息对象
     */
    @Override
    public ExpressConfig queryExpressConfig() {
        ExpressConfigExample expressConfigExample = new ExpressConfigExample();
        return expressConfigMapper.selectByExample(expressConfigExample).get(0);
    }

    /**
     * 更新运费配置信息
     * @param expressConfig
     * @return
     * -1表示服务器繁忙，
     * 0表示更新失败，
     * 1表示更新成功，
     * 2表示更新条件输入不完整，
     * 3表示运费满减所需最低消费不为正数，
     * 4表示运费满减不足所需运费不为正数，
     * 5表示修改参数中存在非数字。
     */
    @Override
    public int updateExpressConfig(ExpressConfig expressConfig) {
        if (StringUtils.isEmpty(expressConfig.getLitemall_express_freight_min()) ||
            StringUtils.isEmpty(expressConfig.getLitemall_express_freight_value())
        ){    //修改条件不完整
            return 2;
        }
        try{
            if (Double.parseDouble(expressConfig.getLitemall_express_freight_min())<=0){    //运费满减所需最低消费需大于0
                return 3;
            }
            if (Double.parseDouble(expressConfig.getLitemall_express_freight_value())<=0){    //运费满减不足所需运费需大于0
                return 4;
            }
        }catch (Exception e){
            return 5;
        }
        ExpressConfig updateExpressConfig = new ExpressConfig(
                1,
                expressConfig.getLitemall_express_freight_min(),
                expressConfig.getLitemall_express_freight_value()
        );
        ExpressConfigExample expressConfigExample = new ExpressConfigExample();
        int result = 0;
        if(expressConfigMapper.selectByExample(expressConfigExample).size()==0){    //表中无运费配置相关数据，插入一条配置信息。
            result = expressConfigMapper.insertSelective(updateExpressConfig);
            return result;
        }
        result = expressConfigMapper.updateByPrimaryKeySelective(updateExpressConfig);    //表中有运费配置相关数据，更新该条配置信息。
        return result;
    }
}
