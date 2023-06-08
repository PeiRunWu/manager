package com.caroLe.manager.thirdPaty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.thirdPaty.service.OssService;

/**
 * @author CaroLe
 * @Date 2023/6/8 21:25
 * @Description
 */
@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping(value = "/uploadOssFile")
    public Result<String> uploadOssFile(@RequestParam("file") MultipartFile file) {
        return ossService.upload(file);
    }

    @DeleteMapping(value = "/deleteOssFile")
    public Result<String> deleteOssFile(@RequestParam String fileName) {
        return ossService.deleteOssFile(fileName);
    }

}