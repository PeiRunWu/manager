package com.caroLe.manager.thirdPaty.service;

import com.caroLe.manager.common.result.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CaroLe
 * @Date 2023/7/23 22:54
 * @Description
 */
public interface CosService {
    /**
     * 上传到腾讯云cos
     * 
     * @param file
     * @param path
     * @return
     */
    Result<String> uploadOssFile(MultipartFile file, String path);

    /**
     * 删除文件cos
     * 
     * @param fileName
     * @return
     */
    Result<String> deleteCosFile(String fileName);
}
