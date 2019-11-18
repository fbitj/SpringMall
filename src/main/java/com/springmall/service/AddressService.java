package com.springmall.service;

import com.springmall.bean.Address;

import java.util.List;

public interface AddressService {
    List<Address> queryAddressList(Integer userId,String  name);
}
