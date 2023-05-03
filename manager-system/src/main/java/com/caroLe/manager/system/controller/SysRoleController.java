package com.caroLe.manager.system.controller;

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
import com.caroLe.manager.system.service.SysRoleService;

/**
 * @author CaroLe
 * @Date 2023/3/6 17:19
 * @Description
 */
@RestController
@RequestMapping("/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/listPage")
    public Result<Page<SysRole>> getPageList(CommonVO commonVO) {
        return sysRoleService.getPageList(commonVO);
    }

    @DeleteMapping("/batchRemove")
    public Result<String> batchRemoveRoleById(@RequestBody List<String> idList) {
        return sysRoleService.batchRemoveRoleById(idList);
    }

    @PostMapping("/saveOrUpdate")
    public Result<String> saveOrUpdateRole(@RequestBody SysRoleVO sysRoleVO) {
        return sysRoleService.saveOrUpdateRole(sysRoleVO);
    }

    @GetMapping("/getAllRole")
    public Result<List<SysRoleDTO>> getAllRoleItem() {
        return sysRoleService.getAllRoleItem();
    }

    @GetMapping("/toAssign/{userId}")
    public Result<Map<String, Object>> toAssign(@PathVariable String userId) {
        return sysRoleService.toAssign(userId);
    }

    @PostMapping("/doAssignRole")
    public Result<String> doAssignRole(@RequestBody AssignRoleVO assignVO) {
        return sysRoleService.doAssign(assignVO);
    }
}
