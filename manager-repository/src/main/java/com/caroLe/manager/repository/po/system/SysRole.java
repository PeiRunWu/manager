package com.caroLe.manager.repository.po.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.caroLe.manager.repository.po.BaseBean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:32
 * @Description
 */
@Data
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseBean {

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色编码")
    private String roleCode;

    @ApiModelProperty("描述")
    private String description;
}
