package com.caroLe.manager.system.service.blog;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.blog.BlogTagDTO;
import com.caroLe.manager.repository.po.blog.BlogTag;
import com.caroLe.manager.repository.vo.blog.BlogTagVO;
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

    /**
     * 保存或更新标签信息
     * 
     * @param blogTagVO
     * @return
     */
    Result<String> saveOrUpdateTag(BlogTagVO blogTagVO);

    /**
     * 获取所有父标签项
     * 
     * @return
     */
    Result<List<BlogTagDTO>> getParentTagItems();

    /**
     * 通过Id删除标签项
     * 
     * @param id
     * @return
     */
    Result<String> removeBlogTagById(String id);
}
