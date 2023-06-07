package com.caroLe.manager.repository.dao.blog;

import org.mapstruct.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caroLe.manager.repository.po.blog.BlogArticle;

/**
 * @author CaroLe
 * @Date 2023/6/6 21:23
 * @Description
 */
@Mapper
public interface BlogArticleDao extends BaseMapper<BlogArticle> {}
