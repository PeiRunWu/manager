package com.caroLe.manager.repository.dto.system;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:36
 * @Description
 */
@Data
public class DataNodeDTO {

    @ApiModelProperty("菜单Id")
    private String key;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("排序")
    private Integer sortValue;

    @ApiModelProperty("菜单子类")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<DataNodeDTO> children;
}
