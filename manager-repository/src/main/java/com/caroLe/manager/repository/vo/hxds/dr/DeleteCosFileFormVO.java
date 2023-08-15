package com.caroLe.manager.repository.vo.hxds.dr;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author CaroLe
 * @Date 2023/7/26 21:28
 * @Description
 */
@Data
public class DeleteCosFileFormVO {

    @NotEmpty(message = "pathes不能为空")
    private String[] pathes;
}
