package com.caroLe.manager.system.service.impl.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caroLe.manager.common.exception.BaseException;
import com.caroLe.manager.common.exception.DataException;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.ErrorType;
import com.caroLe.manager.common.type.SuccessType;
import com.caroLe.manager.repository.dao.system.SysRoleDao;
import com.caroLe.manager.repository.dto.system.SysRoleDTO;
import com.caroLe.manager.repository.po.system.SysRole;
import com.caroLe.manager.repository.po.system.SysUserRole;
import com.caroLe.manager.repository.vo.system.AssignRoleVO;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.repository.vo.system.SysRoleVO;
import com.caroLe.manager.system.service.system.SysRoleService;
import com.caroLe.manager.system.service.system.SysUserRoleService;

import cn.hutool.core.util.ObjectUtil;

/**
 * @author CaroLe
 * @Date 2023/3/6 17:18
 * @Description
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 获取用户角色名称
     *
     * @param commonVO
     * @return
     */
    @Override
    public Result<Page<SysRole>> getPageList(CommonVO commonVO) {
        Page<SysRole> sysRolePage = new Page<>(commonVO.getCurrent(), commonVO.getPageSize());
        LambdaQueryWrapper<SysRole> sysRolQueryWrapper = new LambdaQueryWrapper<>();
        sysRolQueryWrapper.like(ObjectUtil.isNotEmpty(commonVO.getSearchObj()), SysRole::getRoleName,
            commonVO.getSearchObj());
        Page<SysRole> page = baseMapper.selectPage(sysRolePage, sysRolQueryWrapper);
        return Result.success(page, SuccessType.SUCCESS);
    }

    /**
     * 删除用户Id
     *
     * @param idList
     * @return
     */
    @Override
    @Transactional(rollbackFor = BaseException.class)
    public Result<String> batchRemoveRoleById(List<String> idList) {
        int delete = baseMapper.deleteBatchIds(idList);
        if (delete <= 0) {
            throw new DataException(ErrorType.DELETE_DATE);
        }
        return Result.success(null, SuccessType.SUCCESS);
    }

    /**
     * 保存或修改角色
     *
     * @param sysRoleVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = BaseException.class)
    public Result<String> saveOrUpdateRole(SysRoleVO sysRoleVO) {
        SysRole sysRole =
            baseMapper.selectOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleCode, sysRoleVO.getRoleCode()));
        if (ObjectUtil.isNotEmpty(sysRole) && !sysRoleVO.getId().equals(sysRole.getId())) {
            throw new DataException(ErrorType.ROLE_CODE_EXIST);
        }
        sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleVO, sysRole);
        boolean saveOrUpdate = this.saveOrUpdate(sysRole);
        if (!saveOrUpdate) {
            throw new DataException(ErrorType.UPDATE_DATE);
        }
        return Result.success(SuccessType.SUCCESS);
    }

    /**
     * 获取所有角色
     *
     * @return
     */
    @Override
    public Result<List<SysRoleDTO>> getAllRoleItem() {
        List<SysRoleDTO> sysRoleList = new ArrayList<>();
        List<SysRole> sysRoles = baseMapper.selectList(null);
        if (CollectionUtils.isEmpty(sysRoles)) {
            return Result.success(sysRoleList, SuccessType.SUCCESS);
        }
        sysRoles.forEach(sysRole -> {
            SysRoleDTO sysRoleDTO = new SysRoleDTO();
            sysRoleDTO.setRoleName(sysRole.getRoleName());
            sysRoleDTO.setId(sysRole.getId());
            sysRoleList.add(sysRoleDTO);

        });
        return Result.success(sysRoleList, SuccessType.SUCCESS);
    }

    /**
     * 查询当前用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public Result<Map<String, Object>> toAssign(String userId) {
        List<SysRole> allRoleList = baseMapper.selectList(null);
        LambdaQueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new LambdaQueryWrapper<>();
        sysUserRoleQueryWrapper.eq(SysUserRole::getUserId, userId);
        List<SysUserRole> userRoleList = sysUserRoleService.list(sysUserRoleQueryWrapper);
        List<String> roleList = userRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        List<SysRole> assignRoleList = new ArrayList<>();
        for (SysRole sysRole : allRoleList) {
            if (roleList.contains(sysRole.getId())) {
                assignRoleList.add(sysRole);
            }
        }
        Map<String, Object> map = new HashMap<>(4);
        map.put("assignRoleList", assignRoleList);
        map.put("allRolesList", allRoleList);
        return Result.success(map, SuccessType.SUCCESS);
    }

    /**
     * 用户分配权限
     *
     * @param assignVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = BaseException.class)
    public Result<String> doAssign(AssignRoleVO assignVO) {
        if (CollectionUtils.isEmpty(assignVO.getRoleIdList())) {
            throw new DataException(ErrorType.ROLE_EXIST);
        }
        sysUserRoleService
            .remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, assignVO.getUserId()));
        List<String> roleIdList = assignVO.getRoleIdList();
        for (String roleId : roleIdList) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(assignVO.getUserId());
            sysUserRoleService.save(sysUserRole);
        }
        return Result.success(SuccessType.SUCCESS);
    }
}
