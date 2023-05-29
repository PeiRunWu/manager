package com.caroLe.manager.repository.po.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.caroLe.manager.repository.po.BaseBean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CaroLe
 * @Date 2023/5/28 18:04
 * @Description
 */
@Data
@TableName("blog_tag")
@EqualsAndHashCode(callSuper = true)
public class BlogTag extends BaseBean {

    @ApiModelProperty("所属上级")
    private String parentId;

    @ApiModelProperty("标签名称")
    private String tagName;

    @ApiModelProperty("类型(0:父标签,1:子标签)")
    private Integer type;

}
