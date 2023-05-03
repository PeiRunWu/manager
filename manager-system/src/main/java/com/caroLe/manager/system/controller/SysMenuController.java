package com.caroLe.manager.system.controller;

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
import com.caroLe.manager.system.service.SysMenuService;

/**
 * @author CaroLe
 * @Date 2023/3/8 17:49
 * @Description
 */
@RestController
@RequestMapping("/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/listPage")
    public Result<Page<SysMenu>> getPageList(CommonVO commonVO) {
        Page<SysMenu> menuPage = sysMenuService.getPageList(commonVO);
        return Result.success(menuPage, SuccessType.SUCCESS);
    }

    @GetMapping("/updateHidden/{id}/{hidden}")
    public Result<Page<SysMenu>> updateHidden(@PathVariable("id") String id, @PathVariable("hidden") Integer hidden) {
        return sysMenuService.updateHidden(id, hidden);
    }

    @GetMapping("/getAllMenuItems")
    public Result<List<SysMenu>> getAllMenuItems() {
        return sysMenuService.getAllMenuItems();
    }


    @GetMapping("/getAllTableItems")
    public Result<List<SysMenu>> getAllTableItems() {
        return sysMenuService.getAllTableItems();
    }


    @GetMapping("/resourceListPage")
    public Result<Page<SysMenu>> getResourcePageList(CommonVO commonVO) {
        return sysMenuService.getResourcePageList(commonVO);
    }

    @PostMapping("/saveMenu")
    public Result<String> saveMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.success(null, SuccessType.SUCCESS);
    }

    @PutMapping("/updateMenuById")
    public Result<String> updateMenuById(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return Result.success(null, SuccessType.SUCCESS);
    }

    @DeleteMapping("/remove/{id}")
    public Result<String> removeSysMenu(@PathVariable String id) {
        sysMenuService.removeMenu(id);
        return Result.success(null, SuccessType.SUCCESS);
    }

    @GetMapping("/toAssign/{roleId}")
    public Result<AssignAuthMenuDTO> toAssign(@PathVariable String roleId) {
        AssignAuthMenuDTO assignAuthDTO = sysMenuService.findSysMenuByRoleId(roleId);
        return Result.success(assignAuthDTO, SuccessType.SUCCESS);
    }

    @PostMapping("/doAssign")
    public Result<String> doAssign(@RequestBody AssignMenuVO assignMenuVo) {
        sysMenuService.doAssign(assignMenuVo);
        return Result.success(null, SuccessType.SUCCESS);
    }

    @GetMapping("/getUserMenu")
    public Result<List<MenuItemDTO>> getUserMenu() {
        return sysMenuService.getUserMenu();
    }

}
