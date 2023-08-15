package com.caroLe.manager.system.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.system.SysRoleDTO;
import com.caroLe.manager.repository.po.system.SysRole;
import com.caroLe.manager.repository.vo.system.AssignRoleVO;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.repository.vo.system.SysRoleVO;

import java.util.List;
import java.util.Map;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:56
 * @Description
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 获取用户角色名称
     *
     * @param commonVO 搜索条件
     * @return 获取用户角色名称
     */
    Result<Page<SysRole>> getPageList(CommonVO commonVO);

    /**
     * 删除用户Id
     *
     * @param idList id
     * @return Result
     */
    Result<String> batchRemoveRoleById(List<String> idList);

    /**
     * 保存或修改角色
     *
     * @param sysRoleVO 角色信息
     * @return Result
     */
    Result<String> saveOrUpdateRole(SysRoleVO sysRoleVO);

    /**
     * 用户分配权限
     *
     * @param assignVO assignVO信息
     * @return Result
     */
    Result<String> doAssign(AssignRoleVO assignVO);

    /**
     * 查询当前用户角色
     *
     * @param userId 用户Id
     * @return 查询当前用户角色
     */
    Result<Map<String, Object>> toAssign(String userId);

    /**
     * 获取所有角色
     * 
     * @return 获取所有角色
     */
    Result<List<SysRoleDTO>> getAllRoleItem();
}
