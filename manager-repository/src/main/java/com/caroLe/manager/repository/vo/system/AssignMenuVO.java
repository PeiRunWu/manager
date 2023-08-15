package com.caroLe.manager.repository.vo.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:34
 * @Description
 */
@Data
public class AssignMenuVO {

    @ApiModelProperty("角色Id")
    private String roleId;

    @ApiModelProperty("菜单IdList")
    private List<String> menuIdList;
}
