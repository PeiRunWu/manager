package com.caroLe.manager.system.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.blog.BlogArticleDTO;
import com.caroLe.manager.repository.po.blog.BlogArticle;
import com.caroLe.manager.repository.vo.blog.BlogArticleVO;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.system.service.blog.BlogArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CaroLe
 * @Date 2023/6/6 21:19
 * @Description
 */
@RestController
@Api(tags = "后台博客文章管理")
@RequestMapping("/blog/article")
public class BlogArticleController {

    @Autowired
    private BlogArticleService blogArticleService;

    @GetMapping("/listPage")
    @ApiOperation(value = "查询文章分页查询")
    public Result<Page<BlogArticle>> getPageList(CommonVO commonVO,
        @RequestParam(value = "articleTitle", required = false) String articleTitle,
        @RequestParam(value = "articleType", required = false) String articleType) {
        return blogArticleService.getPageList(commonVO, articleTitle, articleType);
    }

    @PutMapping("/updateBlogArticleTop")
    @ApiOperation(value = "更新文章置顶")
    public Result<String> updateBlogArticleTop(@RequestBody BlogArticle blogArticle) {
        return blogArticleService.updateBlogArticleTop(blogArticle);
    }

    @PutMapping("/saveArticle")
    @ApiOperation(value = "保存文章")
    public Result<String> saveArticle(@RequestBody BlogArticleVO blogArticleVO) {
        return blogArticleService.saveArticle(blogArticleVO);
    }

    @DeleteMapping("/removeBlogArticleById/{id}")
    @ApiOperation(value = "删除文章")
    public Result<String> removeBlogArticleById(@PathVariable("id") String id) {
        return blogArticleService.removeBlogArticleById(id);
    }

    @GetMapping("/getBlogArticleById/{id}")
    @ApiOperation(value = "根据Id获取文章")
    public Result<BlogArticleDTO> getBlogArticleById(@PathVariable("id") String id) {
        return blogArticleService.getBlogArticleById(id);
    }

    @PutMapping("/updateBlogArticleById")
    @ApiOperation(value = "根据Id更新文章")
    public Result<String> updateBlogArticleById(@RequestBody BlogArticleVO blogArticleVO) {
        return blogArticleService.updateBlogArticleById(blogArticleVO);
    }

}
