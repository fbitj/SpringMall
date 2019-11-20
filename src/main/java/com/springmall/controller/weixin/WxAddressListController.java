package com.springmall.controller.weixin;

import com.springmall.bean.Address;
import com.springmall.bean.BaseReqVo;
import com.springmall.service.AddressService;
import com.springmall.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
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
        //第一步拿到数据库中的地址信息
        List<Address> addresses=addressService.queryAddressList(1,deleted);

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
     * 进行添加收货地址
     * @param address
     * @return
     */
    @RequestMapping(value = "address/save")
    public BaseReqVo saveAddress(@RequestBody Address address){
        BaseReqVo<Object> objectBaseReqVo = new BaseReqVo<>();
        //使用id进行判断是插入还是进行修改的(0是插入，其他的是修改)
        Integer id = address.getId();
        address.setDeleted(false);
        if(id==0) {
            //进行添加时候的时间
            Date addTime = new Date();
            address.setAddTime(addTime);
            //这里是写死的用户的id
            address.setUserId(1);
            addressService.saveAddress(address);
            objectBaseReqVo.setErrno(0);
            objectBaseReqVo.setData(address.getId());
            objectBaseReqVo.setErrmsg("成功");
            return objectBaseReqVo;
        }
        //id不是0的时候就是进行了修改
        Integer userId=addressService.queryUserId(id);
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
