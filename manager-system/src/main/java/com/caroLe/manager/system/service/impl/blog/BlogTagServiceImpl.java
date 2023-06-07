package com.caroLe.manager.system.service.impl.blog;

import static com.caroLe.manager.common.enums.TagEnum.CHILDREN_TAG;
import static com.caroLe.manager.common.enums.TagEnum.PARENT_TAG;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caroLe.manager.common.context.BaseContext;
import com.caroLe.manager.common.exception.DataException;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.ErrorType;
import com.caroLe.manager.common.type.SuccessType;
import com.caroLe.manager.repository.dao.blog.BlogTagDao;
import com.caroLe.manager.repository.dto.blog.BlogTagDTO;
import com.caroLe.manager.repository.dto.blog.BlogTagTreeNodeDTO;
import com.caroLe.manager.repository.po.blog.BlogTag;
import com.caroLe.manager.repository.vo.blog.BlogTagVO;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.system.service.blog.BlogTagService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
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
     * @param commonVO 标签分页信息
     * @return 博客标签分页查询
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

    /**
     * 保存或更新标签信息
     *
     * @param blogTagVO 标签信息
     * @return 成功信息
     */
    @Override
    @Transactional(rollbackFor = DataException.class)
    public Result<String> saveOrUpdateTag(BlogTagVO blogTagVO) {
        BlogTag blogTag = new BlogTag();
        BeanUtil.copyProperties(blogTagVO, blogTag);
        if (Objects.equals(blogTagVO.getParentId(), BaseContext.ZERO)) {
            if (ObjectUtil.notEqual(blogTagVO.getType(), PARENT_TAG.getCode())) {
                throw new DataException(ErrorType.PARENT_TAG_NOT_SELECT);
            }
        } else {
            if (ObjectUtil.notEqual(blogTagVO.getType(), CHILDREN_TAG.getCode())) {
                throw new DataException(ErrorType.CHILDREN_TAG_NOT_SELECT);
            }
        }
        this.saveOrUpdate(blogTag);
        return Result.success(SuccessType.SUCCESS);
    }

    /**
     * 获取所有父标签项
     *
     * @return 获取所有父标签项
     */
    @Override
    public Result<List<BlogTagDTO>> getParentTagItems() {
        List<BlogTag> blogTags =
            baseMapper.selectList(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getType, PARENT_TAG.getCode()));
        List<BlogTagDTO> blogTagDTOList = blogTags.stream().map(blogTag -> {
            BlogTagDTO blogTagDTO = new BlogTagDTO();
            if (ObjectUtil.isNotEmpty(blogTag)) {
                BeanUtil.copyProperties(blogTag, blogTagDTO);
            }
            return blogTagDTO;
        }).collect(Collectors.toList());
        return Result.success(blogTagDTOList, SuccessType.SUCCESS);
    }

    /**
     * 通过Id删除标签项
     *
     * @param id 主键
     * @return 标签项
     */
    @Override
    public Result<String> removeBlogTagById(String id) {
        List<BlogTag> blogTags = baseMapper.selectList(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getParentId, id));
        if (CollectionUtil.isNotEmpty(blogTags)) {
            throw new DataException(ErrorType.NODE_ERROR);
        }
        baseMapper.deleteById(id);
        return Result.success(SuccessType.SUCCESS);
    }

    /**
     * 获取标签列表以树的形式
     *
     * @return tree
     */
    @Override
    public Result<List<BlogTagTreeNodeDTO>> getBlogTagTreeNode() {
        List<BlogTag> blogTags =
            baseMapper.selectList(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getParentId, BaseContext.ZERO));
        List<BlogTagTreeNodeDTO> blogTagTreeNodeDTOList = blogTags.stream().map(blogTag -> {
            BlogTagTreeNodeDTO blogTagTreeNodeDTO = new BlogTagTreeNodeDTO();
            blogTagTreeNodeDTO.setValue(blogTag.getId());
            blogTagTreeNodeDTO.setTitle(blogTag.getTagName());
            return blogTagTreeNodeDTO;
        }).collect(Collectors.toList());
        blogTagTreeNodeDTOList.forEach(blogTagTreeNodeDTO -> {
            String value = blogTagTreeNodeDTO.getValue();
            List<BlogTag> blogTagList =
                baseMapper.selectList(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getParentId, value));
            if (CollectionUtil.isNotEmpty(blogTagList)) {
                List<BlogTagTreeNodeDTO> childrenTree = blogTagList.stream().map(blogTag -> {
                    BlogTagTreeNodeDTO blogTagTree = new BlogTagTreeNodeDTO();
                    blogTagTree.setValue(blogTag.getId());
                    blogTagTree.setTitle(blogTag.getTagName());
                    return blogTagTree;
                }).collect(Collectors.toList());
                blogTagTreeNodeDTO.setChildren(childrenTree);
            }
        });
        return Result.success(blogTagTreeNodeDTOList, SuccessType.SUCCESS);
    }

}
