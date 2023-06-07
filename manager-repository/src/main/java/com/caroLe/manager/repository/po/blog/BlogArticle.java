package com.caroLe.manager.repository.po.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.caroLe.manager.repository.po.BaseBean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CaroLe
 * @Date 2023/6/5 22:20
 * @Description
 */
@Data
@TableName("blog_article")
@EqualsAndHashCode(callSuper = true)
public class BlogArticle extends BaseBean {

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("添加封面")
    private String cover;

    @ApiModelProperty("文章摘要")
    private String articleSummary;

    @ApiModelProperty("文章类型(0- 原创 1- 转载 2-翻译)")
    private int articleType;

    @ApiModelProperty("可见范围(0- 全部可见 1- 仅我可见)")
    private int visibleRange;

    @ApiModelProperty("是否置顶(1-置顶 0- 不置顶)")
    private String top;
}
