package com.caroLe.manager.repository.vo.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:35
 * @Description
 */
@Data
public class SysRoleVO {

    @ApiModelProperty("角色id")
    private String id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色编码")
    private String roleCode;

    @ApiModelProperty("描述")
    private String description;

}
