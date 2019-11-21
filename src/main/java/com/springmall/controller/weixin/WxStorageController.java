package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Storage;
import com.springmall.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("wx/storage")
public class WxStorageController {
    @Autowired
    StorageService storageService;

    /**
     * "id": 2073,
     * "key": "ktbez8lnolaiw60bkpg8.png",
     * "name": "wx12243f232459a764.o6zAJs9rQ-r2tZMJG-N3z4n9X7fw.aALJSzqYcoHf573c2c798e9f84603f25d433d3e26262.png",
     * "type": "image/png",
     * "size": 625511,
     * "url": "http://192.168.2.100:8081/wx/storage/fetch/ktbez8lnolaiw60bkpg8.png",
     * "addTime": "2019-11-20 09:43:36",
     * "updateTime": "2019-11-20 09:43:36"
     * @param file
     * @return
     */
    @RequestMapping("upload")
    public BaseReqVo upload(MultipartFile file){
        if (!file.getContentType().contains("image")){
            return BaseReqVo.error(500,"请上传图片！");
        }
        Storage storage = storageService.uploadImg(file);
        return BaseReqVo.ok(storage);
    }

}
