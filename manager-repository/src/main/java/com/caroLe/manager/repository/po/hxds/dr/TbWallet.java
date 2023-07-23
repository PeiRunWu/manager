package com.caroLe.manager.repository.po.hxds.dr;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/7/9 21:00
 * @Description
 */
@Data
@TableName("tb_wallet")
public class TbWallet  implements Serializable {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("司机ID")
    private Long driverId;


    @ApiModelProperty("钱包金额")
    private BigDecimal balance;


    @ApiModelProperty("支付密码")
    private String password;

}
