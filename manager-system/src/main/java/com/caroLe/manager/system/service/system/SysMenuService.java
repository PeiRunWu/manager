package com.caroLe.manager.system.service.system;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.system.AssignAuthMenuDTO;
import com.caroLe.manager.repository.dto.system.MenuItemDTO;
import com.caroLe.manager.repository.po.system.SysMenu;
import com.caroLe.manager.repository.vo.system.AssignMenuVO;
import com.caroLe.manager.repository.vo.system.CommonVO;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:55
 * @Description
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 查询菜单
     * 
     * @param commonVO
     * @return
     */
    Page<SysMenu> getPageList(CommonVO commonVO);

    /**
     * 删除菜单
     *
     * @param id
     */
    void removeMenu(String id);

    /**
     * 根据角色获取菜单
     *
     * @param roleId
     * @return
     */
    AssignAuthMenuDTO findSysMenuByRoleId(String roleId);

    /**
     * 分配权限
     *
     * @param assignMenuVo
     */
    void doAssign(AssignMenuVO assignMenuVo);

    /**
     * 获取当前用户菜单权限
     *
     * @return
     */
    Result<List<MenuItemDTO>> getUserMenu();

    /**
     * 修改菜单是否显示
     * 
     * @param id
     * @param hidden
     * @return
     */
    Result<Page<SysMenu>> updateHidden(String id, Integer hidden);

    /**
     * 获取所有的菜单
     * 
     * @return
     */
    Result<List<SysMenu>> getAllMenuItems();

    /**
     * 获取所有资源
     * 
     * @param commonVO
     * @return
     */
    Result<Page<SysMenu>> getResourcePageList(CommonVO commonVO);

    /**
     * 获取所有目录
     * 
     * @return
     */
    Result<List<SysMenu>> getAllTableItems();

    /**
     * 更新菜单或资源
     * 
     * @param sysMenu
     * @return
     */
    Result<String> updateMenuById(SysMenu sysMenu);
}
