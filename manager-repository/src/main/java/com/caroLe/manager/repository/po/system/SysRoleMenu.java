package com.caroLe.manager.repository.po.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.caroLe.manager.repository.po.BaseBean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:33
 * @Description
 */
@Data
@TableName("sys_role_menu")
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends BaseBean {

    @ApiModelProperty("角色Id")
    private String roleId;

    @ApiModelProperty("菜单Id")
    private String menuId;

}
