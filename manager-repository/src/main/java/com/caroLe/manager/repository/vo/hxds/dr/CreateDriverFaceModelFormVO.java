package com.caroLe.manager.repository.vo.hxds.dr;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/8/6 15:26
 * @Description
 */
@Data
public class CreateDriverFaceModelFormVO {

    @ApiModelProperty(value = "司机ID")
    private Long driverId;

    @NotBlank(message = "photo不能为空")
    @ApiModelProperty(value = "司机面部照片Base64字符串")
    private String photo;
}
