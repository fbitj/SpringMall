package com.springmall.service;

import com.springmall.bean.Address;

import java.util.Date;
import java.util.List;

public interface AddressService {
    List<Address> queryAddressList(Integer userId,String  name);
    List<Address> queryAddressList(Integer userId,boolean deleted);
    void saveAddress(Address address);
    Address queryAddress(Integer id);
    void updateAddress(Address address);
    Integer queryUserId(Integer id);
    Date queryAddTime(Integer id);
    void deleteAddress(Integer id);
    void setDefault();

    int selectRegionCode(Integer areaId);
    String selectRegionName(Integer id);
}
