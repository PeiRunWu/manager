package com.caroLe.manager.repository.po.hxds.dr;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CaroLe
 * @Date 2023/7/9 18:37
 * @Description
 */
@Data
@TableName("tb_driver")
public class TbDriver implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("小程序长期授权")
    private String openId;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("头像")
    private String photo;

    @ApiModelProperty("身份证号码")
    private String pid;

    @ApiModelProperty("生日")
    private String birthday;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("收信地址")
    private String mailAddress;

    @ApiModelProperty("应急联系人")
    private String contactName;

    @ApiModelProperty("应急联系人电话")
    private String contactTel;

    @ApiModelProperty("1未认证，2已认证")
    private Integer realAuth;

    @ApiModelProperty("身份证地址")
    private String idcardAddress;

    @ApiModelProperty("身份证有效期")
    private String idcardExpiration;

    @ApiModelProperty("身份证正面")
    private String idcardFront;

    @ApiModelProperty("身份证背面")
    private String idcardBack;

    @ApiModelProperty("手持身份证")
    private String idcardHolding;

    @ApiModelProperty("驾驶证类型")
    private String drcardType;

    @ApiModelProperty("驾驶证有效期")
    private String drcardExpiration;

    @ApiModelProperty("驾驶证初次领证日期")
    private String drcardIssueDate;

    @ApiModelProperty("驾驶证正面")
    private String drcardFront;

    @ApiModelProperty("驾驶证背面")
    private String drcardBack;

    @ApiModelProperty("手持驾驶证")
    private String drcardHolding;

    @ApiModelProperty("家庭地址，包含地址和定位，用于回家导航")
    private String home;

    @ApiModelProperty("摘要信息，level等级，totalOrder接单数，weekOrder周接单，weekComment周好评，appeal正在申诉量")
    private String summary;

    @ApiModelProperty("是否在腾讯云归档存放司机面部信息")
    private Boolean archive;

    @ApiModelProperty("状态，1正常，2禁用，3.降低接单量")
    private Integer status;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
