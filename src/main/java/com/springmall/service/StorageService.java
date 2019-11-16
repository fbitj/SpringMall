package com.springmall.service;

import com.springmall.bean.Storage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface StorageService {

    String storageImg(MultipartFile myfile, HttpServletRequest request) throws IOException;

    int addStorage(Storage storage);
}
