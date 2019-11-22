package com.springmall.controller.weixin;

import com.springmall.bean.Address;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.User;
import com.springmall.exception.ResExceptionHandler;
import com.springmall.service.AddressService;
import com.springmall.service.RegionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("wx")
public class WxAddressListController {
    @Autowired
    AddressService addressService;
    @Autowired
    RegionService regionService;

    /**
     * 获取的是收货地址的列表
     * @return
     */
    @RequestMapping("address/list")
    public BaseReqVo<List<Address>> queryAddressList(){
        BaseReqVo<List<Address>> listBaseReqVo = new BaseReqVo<>();
        //进行逻辑删除的标识
        boolean deleted=false;//false是代表为0，其他是有效的信息
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        Integer id = user.getId();
        //第一步拿到数据库中的地址信息
        List<Address> addresses=addressService.queryAddressList(id,deleted);
        //这一步需要将对应的省，市，区的id进行拼接到address上面
        for (Address address1 : addresses) {
            Integer provinceId = address1.getProvinceId();
            Integer cityId = address1.getCityId();
            Integer areaId = address1.getAreaId();
           String province=regionService.queryRegionName(provinceId);
           String city=regionService.queryRegionName(cityId);
           String area=regionService.queryRegionName(areaId);
           String address = address1.getAddress();
           address1.setDetailedAddress(province+city+area+address);
        }
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(addresses);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }

    /**
     * 进行添加收货地址和修改收货地址
     * @param address
     * @return
     */
    @RequestMapping(value = "address/save")
    public BaseReqVo saveAddress(@RequestBody Address address){
        BaseReqVo<Object> objectBaseReqVo = new BaseReqVo<>();
        //使用id进行判断是插入还是进行修改的(0是插入，其他的是修改)
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        Integer userId=user.getId();
        Integer id = address.getId();
        address.setDeleted(false);
        if(id==0) {
            //需要先进行查一次表，如果addressList的长度为0，则必须将收获地址设置为默认的收获地址
            List<Address> addressList=addressService.queryAddressList(userId,false);
            if(addressList.size()==0){
                //这个是第一次进行插入收获地址
                Date addTime = new Date();
                address.setAddTime(addTime);
                address.setUserId(userId);
                if(address.getIsDefault().equals(false)){
                    //ResExceptionHandler resExceptionHandler = new ResExceptionHandler();
                    //return resExceptionHandler.handleCustomException(new HttpMessageNotReadableException("第一次设置的收货地址必须为默认值"));
                    address.setIsDefault(true);
                    addressService.saveAddress(address);
                    objectBaseReqVo.setErrno(0);
                    objectBaseReqVo.setData(address.getId());
                    objectBaseReqVo.setErrmsg("成功");
                    return objectBaseReqVo;

                }
                addressService.saveAddress(address);
                objectBaseReqVo.setErrno(0);
                objectBaseReqVo.setData(address.getId());
                objectBaseReqVo.setErrmsg("成功");
                return objectBaseReqVo;
            }
            //进行添加时候的时间
            Date addTime = new Date();
            address.setAddTime(addTime);
            address.setUserId(userId);
            //添加的时候也要进行判断是否设置为默认的地址
            if(address.getIsDefault().equals(true)){
                //将所有的设置为非默认地址
                addressService.setDefault();
                addressService.saveAddress(address);
            }else{
                addressService.saveAddress(address);
            }
            objectBaseReqVo.setErrno(0);
            objectBaseReqVo.setData(address.getId());
            objectBaseReqVo.setErrmsg("成功");
            return objectBaseReqVo;
        }
        //id不是0的时候就是进行了修改
       // Integer userId=addressService.queryUserId(id);
        Date addTime=addressService.queryAddTime(id);
        address.setAddTime(addTime);
        address.setUserId(userId);
        Date updateTime=new Date();
        address.setUpdateTime(updateTime);
        //需要处理一步就是isDefault是true还是false，如果是true需要进行两次操作
        if(address.getIsDefault().equals(true)){
            //将所有的设置为非默认地址
           addressService.setDefault();
           addressService.updateAddress(address);
        }else{
            addressService.updateAddress(address);
        }
        objectBaseReqVo.setErrno(0);
        objectBaseReqVo.setData(address.getId());
        objectBaseReqVo.setErrmsg("成功");
        return objectBaseReqVo;
    }

    /**
     * 收货地址详情
     * @param id
     * @return
     */
    @RequestMapping("address/detail")
    public BaseReqVo<Address> queryAddressDetail(Integer id){
        BaseReqVo<Address> addressBaseReqVo = new BaseReqVo<>();
        Address address=addressService.queryAddress(id);
        String provinceName = regionService.queryRegionName(address.getProvinceId());
        address.setProvinceName(provinceName);
        String cityName = regionService.queryRegionName(address.getCityId());
        address.setCityName(cityName);
        String areaName = regionService.queryRegionName(address.getAreaId());
        address.setAreaName(areaName);
        addressBaseReqVo.setErrno(0);
        addressBaseReqVo.setData(address);
        addressBaseReqVo.setErrmsg("成功");
        return  addressBaseReqVo;
    }

    /**
     * 进行了逻辑删除收货地址
     * @param address
     * @return
     */
    @RequestMapping("address/delete")
    public BaseReqVo deleteAddress(@RequestBody Address address){
        BaseReqVo<Object> objectBaseReqVo = new BaseReqVo<>();
        Integer id = address.getId();
        addressService.deleteAddress(id);
        objectBaseReqVo.setErrno(0);
        objectBaseReqVo.setErrmsg("成功");
        return objectBaseReqVo;
    }

}
