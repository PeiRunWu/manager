package com.caroLe.manager.repository.po.hxds.dr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/7/9 21:02
 * @Description
 */
@Data
@TableName("tb_wallet_income")
public class TbWalletIncome implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("uuid字符串")
    private String uuid;

    @ApiModelProperty("司机ID")
    private Long driverId;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("1充值，2奖励，3补贴")
    private Byte type;

    @ApiModelProperty("预支付订单ID")
    private String prepayId;

    @ApiModelProperty("1未支付，2已支付，3已到账")
    private Byte status;

    @ApiModelProperty("备注信息")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
