package com.caroLe.manager.thirdPaty.service;

import org.springframework.web.multipart.MultipartFile;

import com.caroLe.manager.common.result.Result;

/**
 * @author CaroLe
 * @Date 2023/6/8 21:28
 * @Description
 */
public interface OssService {
    /**
     * 上传文件到阿里云oss
     *
     * @param file 文件信息
     * @return 上传地址
     */
    Result<String> upload(MultipartFile file);

    /**
     * 删除文件阿里云oss
     *
     * @param fileName 文件名称
     * @return 成功
     */
    Result<String> deleteOssFile(String fileName);
}
