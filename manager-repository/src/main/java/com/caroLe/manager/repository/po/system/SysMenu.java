package com.caroLe.manager.repository.po.system;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:31
 * @Description
 */
@Data
@TableName("sys_menu")
public class SysMenu {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("菜单Id")
    private String id;

    @ApiModelProperty("所属上级")
    private String parentId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("类型(0:目录,1:菜单,2:按钮)")
    private Integer type;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("排序")
    private Integer sortValue;

    @ApiModelProperty("是否显示")
    private Integer hidden;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
