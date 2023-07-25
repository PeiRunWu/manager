package com.caroLe.manager.thirdPaty.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.thirdPaty.service.WxService;

/**
 * @author CaroLe
 * @Date 2023/7/9 21:46
 * @Description
 */
@RestController
@Api(tags = "微信小程序")
@RequestMapping("/wx")
public class WxController {

    @Autowired
    private WxService wxService;

    @GetMapping("/getOpenId")
    @ApiOperation(value = "获取永久授权Id")
    public Result<String> getOpenId(@RequestParam String code) {
        return wxService.getOpenId(code);
    }

    @GetMapping("/getAccessToken")
    @ApiOperation(value = "获取token")
    public Result<String> getAccessToken() {
        return wxService.getAccessToken();
    }

}
