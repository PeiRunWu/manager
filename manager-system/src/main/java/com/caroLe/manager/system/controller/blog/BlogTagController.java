package com.caroLe.manager.system.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.po.blog.BlogTag;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.system.service.blog.BlogTagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author CaroLe
 * @Date 2023/5/28 18:48
 * @Description
 */
@RestController
@RequestMapping("/blog")
@Api(tags = "后台博客标签管理")
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取博客标签分页查询")
    public Result<Page<BlogTag>> getPageList(CommonVO commonVO) {
        return blogTagService.getPageList(commonVO);
    }

}
