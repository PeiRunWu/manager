package com.caroLe.manager.system.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author CaroLe
 * @Date 2023/4/20 15:21
 * @Description
 */
public interface OssService {

    /**
     * 上传文件
     *
     * @param file 文件信息
     * @return 上传地址
     */
    String upload(MultipartFile file);
}
