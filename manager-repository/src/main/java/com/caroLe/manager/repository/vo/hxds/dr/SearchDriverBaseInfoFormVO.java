package com.caroLe.manager.repository.vo.hxds.dr;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author CaroLe
 * @Date 2023/8/13 21:36
 * @Description
 */
@Data
public class SearchDriverBaseInfoFormVO {

    @NotNull(message = "driverId不能为空")
    @Min(value = 1, message = "driverId不能小于1")
    @ApiModelProperty("司机ID")
    private Long driverId;
}
