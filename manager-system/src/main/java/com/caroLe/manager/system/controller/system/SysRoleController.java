package com.caroLe.manager.system.controller.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.system.SysRoleDTO;
import com.caroLe.manager.repository.po.system.SysRole;
import com.caroLe.manager.repository.vo.system.AssignRoleVO;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.repository.vo.system.SysRoleVO;
import com.caroLe.manager.system.service.system.SysRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author CaroLe
 * @Date 2023/3/6 17:19
 * @Description
 */
@RestController
@Api(tags = "后台角色列表管理")
@RequestMapping("/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取用户角色分页查询")
    public Result<Page<SysRole>> getPageList(CommonVO commonVO) {
        return sysRoleService.getPageList(commonVO);
    }

    @DeleteMapping("/batchRemove")
    @ApiOperation(value = "用户角色批量删除")
    public Result<String> batchRemoveRoleById(@RequestBody List<String> idList) {
        return sysRoleService.batchRemoveRoleById(idList);
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "更新或保存用户角色")
    public Result<String> saveOrUpdateRole(@RequestBody SysRoleVO sysRoleVO) {
        return sysRoleService.saveOrUpdateRole(sysRoleVO);
    }

    @GetMapping("/getAllRole")
    @ApiOperation(value = "获取所有角色")
    public Result<List<SysRoleDTO>> getAllRoleItem() {
        return sysRoleService.getAllRoleItem();
    }

    @GetMapping("/toAssign/{userId}")
    @ApiOperation(value = "查询当前用户角色")
    public Result<Map<String, Object>> toAssign(@PathVariable String userId) {
        return sysRoleService.toAssign(userId);
    }

    @PostMapping("/doAssignRole")
    @ApiOperation(value = "用户分配权限")
    public Result<String> doAssignRole(@RequestBody AssignRoleVO assignVO) {
        return sysRoleService.doAssign(assignVO);
    }
}
