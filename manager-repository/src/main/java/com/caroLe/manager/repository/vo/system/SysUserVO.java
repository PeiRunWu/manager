package com.caroLe.manager.repository.vo.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:35
 * @Description
 */
@Data
public class SysUserVO {

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

}
