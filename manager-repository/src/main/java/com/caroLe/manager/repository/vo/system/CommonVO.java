package com.caroLe.manager.repository.vo.system;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:34
 * @Description
 */
@Data
public class CommonVO {

    private String username;

    private String name;

    private String parentId;

    private String path;

    private Long current;

    private Long pageSize;

    private Object searchObj;

    private String createTimeBegin;

    private String createTimeEnd;

}
