package com.caroLe.manager.system.service.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.blog.BlogTagDTO;
import com.caroLe.manager.repository.dto.blog.BlogTagTreeNodeDTO;
import com.caroLe.manager.repository.po.blog.BlogTag;
import com.caroLe.manager.repository.vo.blog.BlogTagVO;
import com.caroLe.manager.repository.vo.system.CommonVO;

import java.util.List;

/**
 * @author CaroLe
 * @Date 2023/5/28 18:29
 * @Description
 */
public interface BlogTagService extends IService<BlogTag> {
    /**
     * 博客标签分页查询
     * 
     * @param commonVO 标签分页信息
     * @return 博客标签分页查询
     */
    Result<Page<BlogTag>> getPageList(CommonVO commonVO);

    /**
     * 保存或更新标签信息
     * 
     * @param blogTagVO 标签信息
     * @return 成功信息
     */
    Result<String> saveOrUpdateTag(BlogTagVO blogTagVO);

    /**
     * 获取所有父标签项
     * 
     * @return 获取所有父标签项
     */
    Result<List<BlogTagDTO>> getParentTagItems();

    /**
     * 通过Id删除标签项
     * 
     * @param id 主键
     * @return 标签项
     */
    Result<String> removeBlogTagById(String id);

    /**
     * 获取标签列表以树的形式
     * 
     * @return tree
     */
    Result< List<BlogTagTreeNodeDTO>> getBlogTagTreeNode();
}
