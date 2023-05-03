package com.caroLe.manager.repository.dto.system;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/4/24 12:51
 * @Description
 */
@Data
public class MenuItemDTO {

    @ApiModelProperty("menuId")
    private String id;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("排序")
    private Integer sortValue;

    @ApiModelProperty("是否显示")
    private Boolean hideInMenu;

    private List<MenuItemDTO> routes;
}
