package com.springmall.service;

import com.springmall.bean.Storage;
import com.springmall.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageMapper storageMapper;

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
}
