package com.caroLe.manager.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.caroLe.manager.system.service.OssService;

/**
 * @author CaroLe
 * @Date 2023/4/20 15:21
 * @Description
 */
@RestController
@RequestMapping
public class OssController {
    @Autowired
    private OssService ossService;

    @PostMapping(value = "/system/oss/file")
    public String uploadOssFile(@RequestParam("file") MultipartFile file) {
        return ossService.upload(file);
    }

}
