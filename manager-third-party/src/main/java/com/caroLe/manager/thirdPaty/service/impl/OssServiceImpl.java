package com.caroLe.manager.thirdPaty.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.caroLe.manager.common.context.BaseContext;
import com.caroLe.manager.common.exception.DataException;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.ErrorType;
import com.caroLe.manager.common.type.SuccessType;
import com.caroLe.manager.thirdPaty.service.OssService;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2023/6/8 21:28
 * @Description
 */
@Slf4j
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
     * @param file 文件信息
     * @return 上传地址
     */
    @Override
    public Result<String> upload(MultipartFile file) {
        String fileName = "manager-admin/" + new DateTime().toString("yyyy-MM-dd") + "/" + IdUtil.simpleUUID()
            + file.getOriginalFilename();
        OSS ossClient = null;
        try {
            InputStream inputStream = file.getInputStream();
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, fileName, inputStream);
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new DataException(ErrorType.FILE_UPLOAD_FAILED);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return Result.success("https://" + bucketName + "." + endpoint + "/" + fileName, SuccessType.SUCCESS);
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     * @return 成功
     */
    @Override
    public Result<String> deleteOssFile(String fileName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            URL url = new URL(fileName);
            String path = url.getPath();
            if (path.startsWith(BaseContext.SEPARATOR)) {
                path = path.substring(1);
            }
            ossClient.deleteObject(bucketName, path);
        } catch (Exception e) {
            log.error("文件删除失败", e);
            throw new DataException(ErrorType.FILE_DELETE_FAILED);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return Result.success(SuccessType.SUCCESS);
    }
}
