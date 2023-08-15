package com.caroLe.manager.repository.po.hxds.dr;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CaroLe
 * @Date 2023/7/9 18:58
 * @Description
 */
@Data
@TableName("tb_driver_settings")
public class TbDriverSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("司机ID")
    private Long driverId;

    @ApiModelProperty("个人设置")
    private String settings;
}
