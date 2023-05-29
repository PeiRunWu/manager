package com.caroLe.manager.repository.dto.blog;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/5/28 21:30
 * @Description
 */
@Data
public class BlogTagDTO {

    @ApiModelProperty("标签Id")
    private String id;

    @ApiModelProperty("父标签Id")
    private String parentId;

    @ApiModelProperty("标签名称")
    private String tagName;

    @ApiModelProperty("类型(0:父标签,1:子标签)")
    private String type;

}
