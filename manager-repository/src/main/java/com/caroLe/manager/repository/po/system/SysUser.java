package com.caroLe.manager.repository.po.system;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import com.caroLe.manager.repository.po.BaseBean;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:33
 * @Description
 */
@Data
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseBean {

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

    @ApiModelProperty("登入时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;
}
