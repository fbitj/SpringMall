package com.springmall.service;

import com.springmall.bean.Ad;
import com.springmall.bean.Address;
import com.springmall.bean.AddressExample;
import com.springmall.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * 收货地址管理-显示所有的地址
     * @param userId
     * @return
     */
    @Override
    public List<Address> queryAddressList(Integer userId,boolean deleted) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(userId).andDeletedEqualTo(deleted);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        return  addresses;
    }

    /**
     * 保存地址到数据库中
     * @param address
     */
    @Override
    public void saveAddress(Address address) {
        addressMapper.insert(address);
    }

    /**
     * 收货地址的详情
     * @param id
     * @return
     */
    @Override
    public Address queryAddress(Integer id) {
        Address address = addressMapper.selectByPrimaryKey(id);
        return  address;
    }

    /**
     * 进行了修改收货地址
     * @param address
     */
    @Override
    public void updateAddress(Address address) {
        //这个就可以进行修改成功了
        int i = addressMapper.updateByPrimaryKey(address);

    }

    @Override
    public Integer queryUserId(Integer id) {
        Integer userId=addressMapper.queryUserId(id);
        return userId;
    }

    @Override
    public Date queryAddTime(Integer id) {
       Date addtime=addressMapper.queryAddTime(id);
       return addtime;
    }

    /**
     * 进行逻辑删除收货地址
     * @param id
     */
    @Override
    public void deleteAddress(Integer id) {
      int i=addressMapper.deleteAddress(id);
    }

    /**
     * 对于修改收货地址的时候，进行的修改
     */
    @Override
    public void setDefault() {
       int update=addressMapper.updateDefault();
    }
}
