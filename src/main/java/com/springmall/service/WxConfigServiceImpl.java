package com.springmall.service;

import com.springmall.bean.WxConfig;
import com.springmall.bean.WxConfigExample;
import com.springmall.mapper.WxConfigMapper;
import com.springmall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxConfigServiceImpl implements WxConfigService {

    @Autowired
    WxConfigMapper wxConfigMapper;

    /**
     * 获取微信小程序配置信息
     * @return 现微信小程序配置信息对象
     */
    @Override
    public WxConfig queryWxConfig() {
        WxConfigExample wxConfigExample = new WxConfigExample();
        return wxConfigMapper.selectByExample(wxConfigExample).get(0);
    }

    /**
     * 更新微信小程序配置信息
     * @param wxConfig
     * @return
     * -1表示服务器繁忙，
     * 0表示更新失败，
     * 1表示更新成功，
     * 2表示更新条件输入不完整，
     * 3表示新品首发栏目商品显示数量为负数，
     * 4表示分类栏目商品显示数量为负数，
     * 5表示分类栏目显示数量为负数，
     * 6表示品牌制造商直供栏目品牌商显示数量为负数
     * 7表示人气推荐栏目商品显示数量为负数
     * 8表示专题精选栏目显示数量为负数
     * 9表示修改参数中要求输入数字处存在非数字。
     */
    @Override
    public int updateWxConfig(WxConfig wxConfig) {
        if (StringUtils.isEmpty(wxConfig.getLitemall_wx_index_new()) ||
            StringUtils.isEmpty(wxConfig.getLitemall_wx_catlog_goods()) ||
            StringUtils.isEmpty(wxConfig.getLitemall_wx_catlog_list()) ||
            StringUtils.isEmpty(wxConfig.getLitemall_wx_share()) ||
            StringUtils.isEmpty(wxConfig.getLitemall_wx_index_brand()) ||
            StringUtils.isEmpty(wxConfig.getLitemall_wx_index_hot()) ||
            StringUtils.isEmpty(wxConfig.getLitemall_wx_index_topic())
        ){    //修改条件不完整
            return 2;
        }
        try{
            if (Integer.parseInt(wxConfig.getLitemall_wx_index_new())<0){    //新品首发栏目商品显示数量不得为负数
                return 3;
            }
            if (Integer.parseInt(wxConfig.getLitemall_wx_catlog_goods())<0){    //分类栏目商品显示数量不得为负数
                return 4;
            }
            if (Integer.parseInt(wxConfig.getLitemall_wx_catlog_list())<0){    //分类栏目显示数量不得为负数
                return 5;
            }
            if (Integer.parseInt(wxConfig.getLitemall_wx_index_brand())<0){    //品牌制造商直供栏目品牌商显示数量不得为负数
                return 6;
            }
            if (Integer.parseInt(wxConfig.getLitemall_wx_index_hot())<0){    //人气推荐栏目商品显示数量不得为负数
                return 7;
            }
            if (Integer.parseInt(wxConfig.getLitemall_wx_index_topic())<0){    //专题精选栏目显示数量不得为负数
                return 8;
            }
        }catch (Exception e){
            return 9;
        }
        WxConfig updateWxConfig = new WxConfig(
                1,
                wxConfig.getLitemall_wx_index_new(),
                wxConfig.getLitemall_wx_catlog_goods(),
                wxConfig.getLitemall_wx_catlog_list(),
                wxConfig.getLitemall_wx_share(),
                wxConfig.getLitemall_wx_index_brand(),
                wxConfig.getLitemall_wx_index_hot(),
                wxConfig.getLitemall_wx_index_topic()
        );
        WxConfigExample wxConfigExample = new WxConfigExample();
        int result = 0;
        if(wxConfigMapper.selectByExample(wxConfigExample).size()==0){    //表中无微信小程序配置相关数据，插入一条配置信息。
            result = wxConfigMapper.insertSelective(updateWxConfig);
            return result;
        }
        result = wxConfigMapper.updateByPrimaryKeySelective(updateWxConfig);    //表中有微信小程序配置相关数据，更新该条配置信息。
        return result;
    }
}