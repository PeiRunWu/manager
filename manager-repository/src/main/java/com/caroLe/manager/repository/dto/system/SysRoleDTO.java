package com.caroLe.manager.repository.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/5/1 14:14
 * @Description
 */
@Data
public class SysRoleDTO {

    @ApiModelProperty("角色id")
    private String id;

    @ApiModelProperty("角色名称")
    private String roleName;
}
