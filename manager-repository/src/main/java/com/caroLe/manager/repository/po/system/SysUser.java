package com.caroLe.manager.repository.po.system;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:33
 * @Description
 */
@Data
public class SysUser {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("用户Id")
    private String id;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String name;

    @ApiModelProperty("手机")
    private String phone;

    @ApiModelProperty("头像路径")
    private String headUrl;

    @ApiModelProperty("部门id")
    private String deptId;

    @ApiModelProperty("岗位id")
    private String postId;

    @ApiModelProperty("微信openId")
    private String openId;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty("登入时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;
}
