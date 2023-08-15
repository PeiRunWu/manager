package com.caroLe.manager.repository.po.hxds.dr;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author CaroLe
 * @Date 2023/7/9 18:44
 * @Description
 */
@Data
@TableName("tb_driver_fine")
public class TbDriverFine implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("司机ID")
    private Long driverId;

    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("罚款金额")
    private BigDecimal amount;

    @ApiModelProperty("备注信息")
    private String remark;

    @ApiModelProperty(" 1未缴纳，2已缴纳")
    private Byte status;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
