package com.caroLe.manager.repository.po.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.caroLe.manager.repository.po.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:31
 * @Description
 */
@Data
@TableName("sys_menu")
@EqualsAndHashCode(callSuper = true)
public class SysMenu  extends BaseBean {

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

}
