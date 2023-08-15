package com.caroLe.manager.thirdPaty.controller;

import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.thirdPaty.service.CosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CaroLe
 * @Date 2023/7/23 22:54
 * @Description
 */
@RestController
@Api(tags = "腾讯云cos")
@RequestMapping("/cos")
public class CosController {

    @Autowired
    private CosService cosService;

    @PostMapping(value = "/uploadCosFile")
    @ApiOperation(value = "上传文件腾讯cos")
    public Result<String> uploadOssFile(@RequestParam("file") MultipartFile file, @RequestParam("path") String path) {
        return cosService.uploadOssFile(file, path);
    }

    @PostMapping(value = "/deleteCosFile")
    @ApiOperation(value = "删除文件腾讯cos")
    public Result<String> deleteCosFile(@RequestParam String fileName) {
        return cosService.deleteCosFile(fileName);
    }

}
