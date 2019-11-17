package com.springmall.service;

import com.springmall.bean.Address;
import com.springmall.bean.AddressExample;
import com.springmall.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理——收货地址——service层
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<Address> queryAddressList(Integer userId, String name) {
        List<Address> addresses = null;
        AddressExample addressExample = new AddressExample();
        if (userId == null && name == null) {
            addresses = addressMapper.selectByExample(addressExample);
        } else if(userId!=null && name == null){
            addressExample.createCriteria().andUserIdEqualTo(userId);
            addresses=addressMapper.selectByExample(addressExample);
        }else if(name != null && userId == null){
            addressExample.createCriteria().andNameLike("%"+name+"%");
            addresses=addressMapper.selectByExample(addressExample);
        }else {
            addressExample.createCriteria().andNameLike("%"+name+"%").andUserIdEqualTo(userId);
           addresses=addressMapper.selectByExample(addressExample);
        }
        return addresses;
    }
}
