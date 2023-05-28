package com.caroLe.manager.system.service.impl.blog;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.SuccessType;
import com.caroLe.manager.repository.dao.blog.BlogTagDao;
import com.caroLe.manager.repository.po.blog.BlogTag;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.system.service.blog.BlogTagService;

import cn.hutool.core.util.ObjectUtil;

/**
 * @author CaroLe
 * @Date 2023/5/28 18:31
 * @Description
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagDao, BlogTag> implements BlogTagService {

    /**
     * 博客标签分页查询
     *
     * @param commonVO
     * @return
     */
    @Override
    public Result<Page<BlogTag>> getPageList(CommonVO commonVO) {
        Page<BlogTag> blogTagPage = new Page<>(commonVO.getCurrent(), commonVO.getPageSize());
        LambdaQueryWrapper<BlogTag> blogTagQueryWrapper = new LambdaQueryWrapper<>();
        blogTagQueryWrapper.eq(ObjectUtil.isNotEmpty(commonVO.getSearchObj()), BlogTag::getParentId,
            commonVO.getSearchObj());
        Page<BlogTag> page = baseMapper.selectPage(blogTagPage, blogTagQueryWrapper);
        return Result.success(page, SuccessType.SUCCESS);
    }
}
