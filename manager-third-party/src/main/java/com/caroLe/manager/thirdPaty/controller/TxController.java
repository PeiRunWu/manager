package com.caroLe.manager.thirdPaty.controller;

import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.thirdPaty.service.TxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author CaroLe
 * @Date 2023/8/6 15:31
 * @Description
 */
@RestController
@Api(tags = "腾讯云api")
@RequestMapping("/tx")
public class TxController {

    @Autowired
    private TxService txService;

    @PostMapping(value = "/createPerson")
    @ApiOperation(value = "创建人员")
    public Result<String> createPerson(@RequestBody Map<String, Object> map) {
        return txService.createPerson(map);
    }
}
