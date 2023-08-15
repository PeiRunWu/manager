package com.caroLe.manager.system.service.impl.blog;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caroLe.manager.repository.dao.blog.BlogArticleTagDao;
import com.caroLe.manager.repository.po.blog.BlogArticleTag;
import com.caroLe.manager.system.service.blog.BlogArticleTagService;
import org.springframework.stereotype.Service;

/**
 * @author CaroLe
 * @Date 2023/6/6 21:50
 * @Description
 */
@Service
public class BlogArticleTagServiceImpl extends ServiceImpl<BlogArticleTagDao, BlogArticleTag>
    implements BlogArticleTagService {}
