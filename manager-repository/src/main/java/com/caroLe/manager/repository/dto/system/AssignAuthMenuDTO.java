package com.caroLe.manager.repository.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:36
 * @Description
 */
@Data
public class AssignAuthMenuDTO {
    @ApiModelProperty("选中menu菜单")
    private List<String> menuIds;

    @ApiModelProperty("菜单节点")
    private List<DataNodeDTO> dataNode;
}
