package com.springmall.controller;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Storage;
import com.springmall.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;


@RestController
@RequestMapping("admin/storage")
public class StorageController {
    @Autowired
    StorageService storageService;
//    @Autowired
//    CommonsMultipartResolver commonsMultipartResolver;
    /*{
	"errno": 0,
	"data": {
		"id": 1413,
		"key": "iax31gm2lzp6ia4svq4w.png",
		"name": "car.png",
		"type": "image/png",
		"size": 625511,
		"url": "http://192.168.2.100:8081/wx/storage/fetch/iax31gm2lzp6ia4svq4w.png",
		"addTime": "2019-11-15 08:31:58",
		"updateTime": "2019-11-15 08:31:58"
	},
	"errmsg": "成功"
    }
    */
    /**
     * 上传商品图片
     * @return
     */
    @RequestMapping("create")
    public BaseReqVo uploadGoodsImg(@RequestParam("file") MultipartFile myfile, HttpServletRequest request){
        BaseReqVo baseReqVo = new BaseReqVo();
        // 1.将图片存到服务器
            String filepath = null;
        try {
            filepath = storageService.storageImg(myfile, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (filepath == null){
            // 图片上传失败===============================待补充=========================
            return baseReqVo;
        }
        // 图片上传成功
        Storage storage = new Storage();
        storage.setKey((filepath.substring(filepath.lastIndexOf('/')+1)));
        storage.setName(myfile.getOriginalFilename());
        storage.setType(myfile.getContentType());
        long size = myfile.getSize();
        storage.setSize((int)size);
        storage.setUrl("http://192.168.2.100:8080/"+filepath.substring(filepath.indexOf("/")+1));
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        storage.setDeleted(false);

        // 2.将图片信息插入到storage表中
        int res = storageService.addStorage(storage);
        //==============这里是否需要判断添加成功================================
        // 3.返回该图片的Storage
        /*baseReqVo.setData();
        baseReqVo.setErrmsg();
        baseReqVo.setErrno();*/
        return baseReqVo;
    }
}
