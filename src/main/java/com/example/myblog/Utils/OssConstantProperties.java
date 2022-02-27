package com.example.myblog.Utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OssConstantProperties implements InitializingBean {

    //读取oss配置文件

    @Value("${aliyun.oss.endpoint}")
    private String endPoint;

    @Value("${aliyun.oss.keyid}")
    private String keyId;

    @Value("${aliyun.oss.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.bucketname}")
    private String bucketName;

    //定义公开static常量
    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endPoint;
        KEY_ID=keyId;
        KEY_SECRET=keySecret;
        BUCKET_NAME=bucketName;
    }
}
