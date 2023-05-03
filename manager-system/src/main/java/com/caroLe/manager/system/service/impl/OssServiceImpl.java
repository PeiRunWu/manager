package com.caroLe.manager.system.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.caroLe.manager.system.service.OssService;

/**
 * @author CaroLe
 * @Date 2023/4/20 15:21
 * @Description
 */
@Service
public class OssServiceImpl implements OssService {

    @Value("${aliCloud.oss.endpoint}")
    private String endpoint;

    @Value("${aliCloud.accessKeyId}")
    private String accessKeyId;

    @Value("${aliCloud.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliCloud.bucketName}")
    private String bucketName;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @Override
    public String upload(MultipartFile file) {
        String fileName = "manager-admin/" + new DateTime().toString("yyyy-MM-dd") + "/"
                + UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
        OSS ossClient = null;
        try {
            InputStream inputStream = file.getInputStream();
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, fileName, inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }
}

