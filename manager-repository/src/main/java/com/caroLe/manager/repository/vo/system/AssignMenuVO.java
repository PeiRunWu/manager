package com.caroLe.manager.repository.vo.system;

import java.util.List;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:34
 * @Description
 */
@Data
public class AssignMenuVO {

    private String roleId;

    private List<String> menuIdList;
}
