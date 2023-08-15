package com.caroLe.manager.repository.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author CaroLe
 * @Date 2023/4/21 15:31
 * @Description
 */
@Data
public class SysMenuSecurityDTO {

    @ApiModelProperty("用户Id")
    private String id;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("客户端Id")
    private String clientId;

    @ApiModelProperty("角色编码")
    private List<String> roleCodeList;
}
