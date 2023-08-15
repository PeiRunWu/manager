package com.caroLe.manager.system.service.impl.blog;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caroLe.manager.common.exception.DataException;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.StatusType;
import com.caroLe.manager.common.type.SuccessType;
import com.caroLe.manager.repository.dao.blog.BlogArticleDao;
import com.caroLe.manager.repository.dto.blog.BlogArticleDTO;
import com.caroLe.manager.repository.dto.system.SysUserDTO;
import com.caroLe.manager.repository.po.blog.BlogArticle;
import com.caroLe.manager.repository.po.blog.BlogArticleTag;
import com.caroLe.manager.repository.vo.blog.BlogArticleVO;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.system.service.blog.BlogArticleService;
import com.caroLe.manager.system.service.blog.BlogArticleTagService;
import com.caroLe.manager.system.service.system.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

/**
 * @author CaroLe
 * @Date 2023/6/6 21:24
 * @Description
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleDao, BlogArticle> implements BlogArticleService {

    @Autowired
    private BlogArticleTagService blogArticleTagService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 保存或修改文章
     *
     * @param blogArticleVO 文章内容信息
     * @return 成功
     */
    @Override
    @Transactional(rollbackFor = DataFormatException.class)
    public Result<String> saveArticle(BlogArticleVO blogArticleVO) {
        BlogArticle blogArticle = new BlogArticle();
        BeanUtil.copyProperties(blogArticleVO, blogArticle);
        Result<SysUserDTO> userInfo = sysUserService.getUserInfo();
        String name = userInfo.getData().getName();
        blogArticle.setAuthor(name);
        baseMapper.insert(blogArticle);
        List<String> articleTag = blogArticleVO.getArticleTag();
        articleTag.forEach(tag -> {
            BlogArticleTag blogArticleTag = new BlogArticleTag();
            blogArticleTag.setTagId(tag);
            blogArticleTag.setArticleId(blogArticle.getId());
            blogArticleTagService.save(blogArticleTag);
        });
        return Result.success(SuccessType.SUCCESS);
    }

    /**
     * 查询文章分页查询
     *
     * @param commonVO 分页查询
     * @param articleTitle 文章标题
     * @param articleType 文章类型
     * @return 分页信息
     */
    @Override
    public Result<Page<BlogArticle>> getPageList(CommonVO commonVO, String articleTitle, String articleType) {
        Page<BlogArticle> blogArticlePage = new Page<>(commonVO.getCurrent(), commonVO.getPageSize());
        LambdaQueryWrapper<BlogArticle> blogArticleQueryWrapper = new LambdaQueryWrapper<>();
        blogArticleQueryWrapper.like(StrUtil.isNotBlank(articleTitle), BlogArticle::getArticleTitle, articleTitle);
        blogArticleQueryWrapper.eq(StrUtil.isNotBlank(articleType), BlogArticle::getArticleType, articleType);
        blogArticleQueryWrapper.orderByDesc(BlogArticle::getTop);
        Page<BlogArticle> page = baseMapper.selectPage(blogArticlePage, blogArticleQueryWrapper);
        return Result.success(page, SuccessType.SUCCESS);
    }

    /**
     * 更新文章置顶
     *
     * @param blogArticle 内容
     * @return 成功
     */
    @Override
    @Transactional(rollbackFor = DataException.class)
    public Result<String> updateBlogArticleTop(BlogArticle blogArticle) {
        BlogArticle article = baseMapper.selectById(blogArticle.getId());
        article.setTop(blogArticle.getTop());
        baseMapper.updateById(article);
        return Result.success(SuccessType.SUCCESS);
    }

    /**
     * 删除文章
     *
     * @param id 文章Id
     * @return 成功
     */
    @Override
    public Result<String> removeBlogArticleById(String id) {
        baseMapper.deleteById(id);
        return Result.success(SuccessType.SUCCESS);
    }

    /**
     * 根据Id获取文章
     *
     * @param id 文章Id
     * @return 文章列表
     */
    @Override
    public Result<BlogArticleDTO> getBlogArticleById(String id) {
        BlogArticle blogArticle = baseMapper.selectById(id);
        BlogArticleDTO blogArticleDTO = new BlogArticleDTO();
        BeanUtil.copyProperties(blogArticle, blogArticleDTO);
        List<BlogArticleTag> blogArticleTagList =
            blogArticleTagService.list(new LambdaQueryWrapper<BlogArticleTag>().eq(BlogArticleTag::getArticleId, id));
        if (CollectionUtil.isNotEmpty(blogArticleTagList)) {
            List<String> tagList =
                blogArticleTagList.stream().map(BlogArticleTag::getTagId).collect(Collectors.toList());
            blogArticleDTO.setArticleTag(tagList);
        }
        return Result.success(blogArticleDTO, StatusType.SUCCESS);
    }

    /**
     * 根据Id更新文章
     *
     * @param blogArticleVO 文章信息
     * @return 成功
     */
    @Override
    @Transactional(rollbackFor = DataException.class)
    public Result<String> updateBlogArticleById(BlogArticleVO blogArticleVO) {
        BlogArticle blogArticle = new BlogArticle();
        BeanUtil.copyProperties(blogArticleVO, blogArticle);
        baseMapper.updateById(blogArticle);
        blogArticleTagService
            .remove(new LambdaQueryWrapper<BlogArticleTag>().eq(BlogArticleTag::getArticleId, blogArticleVO.getId()));
        List<String> tagList = blogArticleVO.getArticleTag();
        if (CollectionUtil.isNotEmpty(tagList)) {
            tagList.forEach(tag -> {
                BlogArticleTag blogArticleTag = new BlogArticleTag();
                blogArticleTag.setArticleId(blogArticleVO.getId());
                blogArticleTag.setTagId(tag);
                blogArticleTagService.save(blogArticleTag);
            });
        }
        return Result.success(SuccessType.SUCCESS);
    }
}
