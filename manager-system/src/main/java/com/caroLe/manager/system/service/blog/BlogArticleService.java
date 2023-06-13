package com.caroLe.manager.system.service.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.blog.BlogArticleDTO;
import com.caroLe.manager.repository.po.blog.BlogArticle;
import com.caroLe.manager.repository.vo.blog.BlogArticleVO;
import com.caroLe.manager.repository.vo.system.CommonVO;

/**
 * @author CaroLe
 * @Date 2023/6/6 21:22
 * @Description
 */
public interface BlogArticleService extends IService<BlogArticle> {
    /**
     * 保存或修改文章
     * 
     * @param blogArticleVO 文章内容信息
     * @return 成功
     */
    Result<String> saveArticle(BlogArticleVO blogArticleVO);

    /**
     * 查询文章分页查询
     * 
     * @param commonVO 分页查询
     * @param articleTitle 文章标题
     * @param articleType 文章类型
     * @return 分页信息
     */
    Result<Page<BlogArticle>> getPageList(CommonVO commonVO, String articleTitle, String articleType);

    /**
     * 更新文章置顶
     * 
     * @param blogArticle 内容
     * @return 成功
     */
    Result<String> updateBlogArticleTop(BlogArticle blogArticle);

    /**
     * 删除文章
     * 
     * @param id 文章Id
     * @return 成功
     */
    Result<String> removeBlogArticleById(String id);

    /**
     * 根据Id获取文章
     * 
     * @param id 文章Id
     * @return 文章列表
     */
    Result<BlogArticleDTO> getBlogArticleById(String id);

    /**
     * 根据Id更新文章
     * 
     * @param blogArticleVO 文章信息
     * @return 成功
     */
    Result<String> updateBlogArticleById(BlogArticleVO blogArticleVO);
}
