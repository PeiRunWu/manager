package com.caroLe.manager.repository.vo.hxds.dr;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/7/9 20:49
 * @Description
 */
@Data
public class RegisterNewDriverFormVO {

    @NotBlank(message = "code不能为空")
    @ApiModelProperty("微信小程序临时授权")
    private String code;

    @NotBlank(message = "nickname不能为空")
    @ApiModelProperty("用户昵称")
    private String nickname;

    @NotBlank(message = "photo不能为空")
    @ApiModelProperty("用户头像")
    private String photo;
}
