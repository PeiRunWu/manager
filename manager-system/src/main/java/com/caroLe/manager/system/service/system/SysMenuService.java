package com.caroLe.manager.system.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.system.AssignAuthMenuDTO;
import com.caroLe.manager.repository.dto.system.MenuItemDTO;
import com.caroLe.manager.repository.po.system.SysMenu;
import com.caroLe.manager.repository.vo.system.AssignMenuVO;
import com.caroLe.manager.repository.vo.system.CommonVO;

import java.util.List;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:55
 * @Description
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 查询菜单
     * 
     * @param commonVO 搜索条件
     * @return 分页信息
     */
    Page<SysMenu> getPageList(CommonVO commonVO);

    /**
     * 删除菜单
     *
     * @param id 菜单Id
     */
    void removeMenu(String id);

    /**
     * 根据角色获取菜单
     *
     * @param roleId 角色Id
     * @return 菜单信息
     */
    AssignAuthMenuDTO findSysMenuByRoleId(String roleId);

    /**
     * 分配权限
     *
     * @param assignMenuVo 权限VO
     */
    void doAssign(AssignMenuVO assignMenuVo);

    /**
     * 获取当前用户菜单权限
     *
     * @return 获取当前用户菜单权限
     */
    Result<List<MenuItemDTO>> getUserMenu();

    /**
     * 修改菜单是否显示
     * 
     * @param id 菜单Id
     * @param hidden 是否显示
     * @return Result
     */
    Result<String> updateHidden(String id, Integer hidden);

    /**
     * 获取所有的菜单
     * 
     * @return 获取所有的菜单
     */
    Result<List<SysMenu>> getAllMenuItems();

    /**
     * 获取所有资源
     * 
     * @param commonVO 搜索条件
     * @return 获取所有资源
     */
    Result<Page<SysMenu>> getResourcePageList(CommonVO commonVO);

    /**
     * 获取所有目录
     * 
     * @return 获取所有目录
     */
    Result<List<SysMenu>> getAllTableItems();

    /**
     * 更新菜单或资源
     * 
     * @param sysMenu 菜单信息
     * @return 更新菜单或资源
     */
    Result<String> updateMenuById(SysMenu sysMenu);
}
