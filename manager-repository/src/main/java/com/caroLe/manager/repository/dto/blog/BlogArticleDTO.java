package com.caroLe.manager.repository.dto.blog;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/6/13 22:09
 * @Description
 */
@Data
public class BlogArticleDTO {

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("文章标签")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> articleTag;

    @ApiModelProperty("添加封面")
    private String cover;

    @ApiModelProperty("文章摘要")
    private String articleSummary;

    @ApiModelProperty("文章类型(0- 原创 1- 转载 2-翻译)")
    private Integer articleType;

    @ApiModelProperty("可见范围(0- 全部可见 1- 仅我可见)")
    private Integer visibleRange;

    @ApiModelProperty("是否置顶(1-置顶 0- 不置顶)")
    private Integer top;
}
