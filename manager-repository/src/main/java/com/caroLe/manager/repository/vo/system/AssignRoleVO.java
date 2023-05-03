package com.caroLe.manager.repository.vo.system;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:34
 * @Description
 */
@Data
public class AssignRoleVO {

    @ApiModelProperty("用户Id")
    private String userId;

    @ApiModelProperty("角色Id列表")
    private List<String> roleIdList;

}
