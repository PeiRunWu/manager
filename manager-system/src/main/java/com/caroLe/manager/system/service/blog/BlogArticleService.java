package com.caroLe.manager.system.service.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.po.blog.BlogArticle;
import com.caroLe.manager.repository.vo.blog.BlogArticleVO;

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
}
