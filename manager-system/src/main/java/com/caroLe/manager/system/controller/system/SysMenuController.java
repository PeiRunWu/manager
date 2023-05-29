package com.caroLe.manager.system.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.SuccessType;
import com.caroLe.manager.repository.dto.system.AssignAuthMenuDTO;
import com.caroLe.manager.repository.dto.system.MenuItemDTO;
import com.caroLe.manager.repository.po.system.SysMenu;
import com.caroLe.manager.repository.vo.system.AssignMenuVO;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.system.service.system.SysMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author CaroLe
 * @Date 2023/3/8 17:49
 * @Description
 */
@RestController
@Api(tags = "后台菜单列表管理")
@RequestMapping("/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/listPage")
    @ApiOperation(value = "查询菜单分页查询")
    public Result<Page<SysMenu>> getPageList(CommonVO commonVO) {
        Page<SysMenu> menuPage = sysMenuService.getPageList(commonVO);
        return Result.success(menuPage, SuccessType.SUCCESS);
    }

    @GetMapping("/updateHidden/{id}/{hidden}")
    @ApiOperation(value = "修改菜单是否显示")
    public Result<String> updateHidden(@PathVariable("id") String id, @PathVariable("hidden") Integer hidden) {
        return sysMenuService.updateHidden(id, hidden);
    }

    @GetMapping("/getAllMenuItems")
    @ApiOperation(value = "获取所有的菜单")
    public Result<List<SysMenu>> getAllMenuItems() {
        return sysMenuService.getAllMenuItems();
    }

    @GetMapping("/getAllTableItems")
    @ApiOperation(value = "获取所有目录")
    public Result<List<SysMenu>> getAllTableItems() {
        return sysMenuService.getAllTableItems();
    }

    @GetMapping("/resourceListPage")
    @ApiOperation(value = "获取所有资源")
    public Result<Page<SysMenu>> getResourcePageList(CommonVO commonVO) {
        return sysMenuService.getResourcePageList(commonVO);
    }

    @PostMapping("/saveMenu")
    @ApiOperation(value = "保存菜单")
    public Result<String> saveMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.success(SuccessType.SUCCESS);
    }

    @PutMapping("/updateMenuById")
    @ApiOperation(value = "更新菜单或资源")
    public Result<String> updateMenuById(@RequestBody SysMenu sysMenu) {
        return sysMenuService.updateMenuById(sysMenu);
    }

    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "删除菜单")
    public Result<String> removeSysMenu(@PathVariable String id) {
        sysMenuService.removeMenu(id);
        return Result.success(SuccessType.SUCCESS);
    }

    @GetMapping("/toAssign/{roleId}")
    @ApiOperation(value = "根据角色获取菜单")
    public Result<AssignAuthMenuDTO> toAssign(@PathVariable String roleId) {
        AssignAuthMenuDTO assignAuthDTO = sysMenuService.findSysMenuByRoleId(roleId);
        return Result.success(assignAuthDTO, SuccessType.SUCCESS);
    }

    @PostMapping("/doAssign")
    @ApiOperation(value = "分配权限")
    public Result<String> doAssign(@RequestBody AssignMenuVO assignMenuVo) {
        sysMenuService.doAssign(assignMenuVo);
        return Result.success(SuccessType.SUCCESS);
    }

    @GetMapping("/getUserMenu")
    @ApiOperation(value = "获取当前用户菜单权限")
    public Result<List<MenuItemDTO>> getUserMenu() {
        return sysMenuService.getUserMenu();
    }

}
