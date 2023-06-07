package com.caroLe.manager.system.service.impl.blog;

import java.util.List;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caroLe.manager.common.exception.UserNameNotFound;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.ErrorType;
import com.caroLe.manager.common.type.SuccessType;
import com.caroLe.manager.repository.dao.blog.BlogArticleDao;
import com.caroLe.manager.repository.dto.system.SysUserDTO;
import com.caroLe.manager.repository.po.blog.BlogArticle;
import com.caroLe.manager.repository.po.blog.BlogArticleTag;
import com.caroLe.manager.repository.vo.blog.BlogArticleVO;
import com.caroLe.manager.system.service.blog.BlogArticleService;
import com.caroLe.manager.system.service.blog.BlogArticleTagService;
import com.caroLe.manager.system.service.system.SysUserService;

import cn.hutool.core.bean.BeanUtil;

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
        if (!userInfo.getCode().equals(SuccessType.SUCCESS.getCode())) {
            throw new UserNameNotFound(ErrorType.LOGIN_AUTH);
        }
        String username = userInfo.getData().getUsername();
        blogArticle.setAuthor(username);
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
}
