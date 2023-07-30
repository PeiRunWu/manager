package com.caroLe.manager.thirdPaty.service;

import org.springframework.web.multipart.MultipartFile;

import com.caroLe.manager.common.result.Result;

/**
 * @author CaroLe
 * @Date 2023/7/23 22:54
 * @Description
 */
public interface CosService {
    /**
     * 上传到腾讯云cos
     * 
     * @param file 文件
     * @param path 路径
     * @return cos路径
     */
    Result<String> uploadOssFile(MultipartFile file, String path);

    /**
     * 删除文件cos
     * 
     * @param fileName 文件路径
     * @return 成功
     */
    Result<String> deleteCosFile(String fileName);
}
