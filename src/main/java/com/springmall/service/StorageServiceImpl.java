package com.springmall.service;
import java.util.Date;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Storage;
import com.springmall.component.AliyunComponent;
import com.springmall.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageMapper storageMapper;
    @Autowired
    AliyunComponent aliyunComponent;

    /**
     * 添加图片信息
     * @param storage
     * @return
     */
    @Override
    public int addStorage(Storage storage) {
        return storageMapper.insertSelective(storage);
    }

    /**
     * 将图片保存到服务器本地
     * @param myfile
     * @param request
     * @return
     * @throws IOException
     */
    @Override
    public String storageImg(MultipartFile myfile, HttpServletRequest request) throws IOException {
        // 获取到文件名
        String filename = myfile.getOriginalFilename();
        // 改变文件名
        UUID uuid = UUID.randomUUID();
        filename=uuid + filename.substring(filename.indexOf('.'));
        // 获取到项目根目录
//        String realPath = request.getServletContext().getRealPath("");

        String filePath = "H:/static/storage/goodsImg/" + filename;
        // 存入服务器
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        myfile.transferTo(file);
        return filePath;
    }

    @Override
    public Storage uploadImg(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OSSClient ossClient = aliyunComponent.getOssClient();
        String filename = UUID.randomUUID().toString().replaceAll("-", "");
        filename += ".jpg";
        ossClient.putObject(aliyunComponent.getOss().getBucket(),filename,inputStream);
        String url = "http://"+aliyunComponent.getOss().getBucket()+"."+aliyunComponent.getOss().getEndPoint()+"/"+filename;
        Storage storage = new Storage();
        storage.setKey(filename);
        storage.setName(file.getName());
        storage.setType(file.getContentType());
        storage.setSize((int) file.getSize());
        storage.setUrl(url);
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        storage.setDeleted(false);
        storageMapper.insertSelective(storage);
        return storage;
    }
}
