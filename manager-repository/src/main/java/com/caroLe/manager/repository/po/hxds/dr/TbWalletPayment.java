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
 * @Date 2023/7/9 21:04
 * @Description
 */
@Data
@TableName("tb_wallet_payment")
public class TbWalletPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("司机ID")
    private Long driverId;

    @ApiModelProperty("支付金额")
    private BigDecimal amount;

    @ApiModelProperty("1话费，2罚款，3抽奖，4缴费，5其他")
    private Byte type;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
