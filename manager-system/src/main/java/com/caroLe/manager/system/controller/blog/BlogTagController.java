package com.caroLe.manager.system.controller.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.blog.BlogTagDTO;
import com.caroLe.manager.repository.po.blog.BlogTag;
import com.caroLe.manager.repository.vo.blog.BlogTagVO;
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
@RequestMapping("/blog/tag")
@Api(tags = "后台博客标签管理")
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取博客标签分页查询")
    public Result<Page<BlogTag>> getPageList(CommonVO commonVO) {
        return blogTagService.getPageList(commonVO);
    }

    @PutMapping("/saveOrUpdateTag")
    @ApiOperation(value = "保存或更新标签信息")
    public Result<String> saveOrUpdateTag(@RequestBody BlogTagVO blogTagVO) {
        return blogTagService.saveOrUpdateTag(blogTagVO);

    }

    @GetMapping("/getParentTagItems")
    @ApiOperation(value = "获取所有父标签项")
    public Result<List<BlogTagDTO>> getParentTagItems() {
        return blogTagService.getParentTagItems();
    }

    @DeleteMapping("/removeBlogTagById/{id}")
    @ApiOperation(value = "通过Id删除标签项")
    public Result<String> removeBlogTagById(@PathVariable("id") String id) {
        return blogTagService.removeBlogTagById(id);
    }

}
