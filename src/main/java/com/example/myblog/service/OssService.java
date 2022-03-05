package com.example.myblog.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    //上传文件到oss
    String uploadBlog(MultipartFile file);

}
