package com.caroLe.manager.repository.dto.blog;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author CaroLe
 * @Date 2023/6/5 21:14
 * @Description
 */
@Data
public class BlogTagTreeNodeDTO {

    @ApiModelProperty("标签名称")
    private String title;

    @ApiModelProperty("标签Id")
    private String value;

    @ApiModelProperty("子类")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BlogTagTreeNodeDTO> children;
}
