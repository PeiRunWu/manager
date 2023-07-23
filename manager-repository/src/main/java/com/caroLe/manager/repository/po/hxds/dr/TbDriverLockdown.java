package com.caroLe.manager.repository.po.hxds.dr;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/7/9 19:05
 * @Description
 */
@Data
@TableName("tb_driver_lockdown")
public class TbDriverLockdown implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("司机ID")
    private Long driverId;

    @ApiModelProperty("原因")
    private String reason;

    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("起始日期")
    private Date startDate;

    @ApiModelProperty("结束日期")
    private Date endDate;

    @ApiModelProperty("天数")
    private Integer days;
}
