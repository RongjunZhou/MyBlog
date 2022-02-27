package com.example.myblog.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.myblog.Utils.OssConstantProperties;
import com.example.myblog.service.OssService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadBlog(MultipartFile file) {
        try{
            //同步工具类
            String endpoint = OssConstantProperties.END_POINT;
            String accessKeyId = OssConstantProperties.KEY_ID;
            String accessKeySecret = OssConstantProperties.KEY_SECRET;
            String bucketName = OssConstantProperties.BUCKET_NAME;

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //上传文件流
            InputStream inputStream=file.getInputStream();

            //获取文件名称
            String filename = file.getOriginalFilename();

            //调用Oss方法实现上传
            ossClient.putObject(bucketName,filename,inputStream);

            //关闭OssClient
            ossClient.shutdown();

            //返回文件路径
            return "https://"+bucketName+"."+endpoint+"/"+filename;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
