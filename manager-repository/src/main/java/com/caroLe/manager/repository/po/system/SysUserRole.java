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
@TableName("sys_user_role")
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends BaseBean {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("角色id")
    private String roleId;

}