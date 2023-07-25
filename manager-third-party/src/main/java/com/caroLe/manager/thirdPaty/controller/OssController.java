package com.caroLe.manager.thirdPaty.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "阿里云oss")
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping(value = "/uploadOssFile")
    @ApiOperation(value = "文件上传阿里云oss")
    public Result<String> uploadOssFile(@RequestParam("file") MultipartFile file) {
        return ossService.upload(file);
    }

    @DeleteMapping(value = "/deleteOssFile")
    @ApiOperation(value = "文件删除阿里云oss")
    public Result<String> deleteOssFile(@RequestParam String fileName) {
        return ossService.deleteOssFile(fileName);
    }

}