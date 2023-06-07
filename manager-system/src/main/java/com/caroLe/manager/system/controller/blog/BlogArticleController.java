package com.caroLe.manager.system.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.vo.blog.BlogArticleVO;
import com.caroLe.manager.system.service.blog.BlogArticleService;

import io.swagger.annotations.ApiOperation;

/**
 * @author CaroLe
 * @Date 2023/6/6 21:19
 * @Description
 */
@RestController
@RequestMapping("/blog/article")
public class BlogArticleController {

    @Autowired
    private BlogArticleService blogArticleService;

    @PutMapping("/saveArticle")
    @ApiOperation(value = "保存文章")
    public Result<String> saveArticle(@RequestBody BlogArticleVO blogArticleVO) {
        return blogArticleService.saveArticle(blogArticleVO);
    }
}
