package com.caroLe.manager.system.service.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.po.blog.BlogTag;
import com.caroLe.manager.repository.vo.system.CommonVO;

/**
 * @author CaroLe
 * @Date 2023/5/28 18:29
 * @Description
 */
public interface BlogTagService extends IService<BlogTag> {
    /**
     * 博客标签分页查询
     * 
     * @param commonVO
     * @return
     */
    Result<Page<BlogTag>> getPageList(CommonVO commonVO);
}
