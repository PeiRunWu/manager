package com.caroLe.manager.repository.po.blog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/6/6 21:48
 * @Description
 */
@Data
public class BlogArticleTag {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("作者")
    private String tagId;

    @ApiModelProperty("文章标题")
    private String articleId;
}
