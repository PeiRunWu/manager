package com.caroLe.manager.repository.vo.hxds.dr;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author CaroLe
 * @Date 2023/8/6 18:13
 * @Description
 */
@Data
public class LoginFormVO {

    @NotBlank(message = "code不能为空")
    @ApiModelProperty(value = "微信小程序临时授权")
    private String code;

    @NotBlank(message = "phoneCode不能为空")
    @ApiModelProperty(value = "微信小程序获取电话号码临时授权")
    private String phoneCode;
}
