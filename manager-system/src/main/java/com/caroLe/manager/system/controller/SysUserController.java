package com.caroLe.manager.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.system.SysMenuSecurityDTO;
import com.caroLe.manager.repository.dto.system.SysUserDTO;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.repository.vo.system.SysUserVO;
import com.caroLe.manager.system.service.SysUserService;

/**
 * @author CaroLe
 * @Date 2023/2/28 20:44
 * @Description
 */
@RestController
@RequestMapping("/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/register")
    public Result<String> registerUser(@RequestBody SysUserVO sysUserVO) {
        return sysUserService.registerUser(sysUserVO);
    }

    @GetMapping("/info")
    public Result<SysUserDTO> getUserInfo() {
        return sysUserService.getUserInfo();
    }

    @GetMapping("/listPage")
    public Result<Page<SysUserDTO>> listPage(CommonVO commonVO) {
        return sysUserService.listPage(commonVO);
    }

    @DeleteMapping("/delete/{id}")
    public Result<String> deleteSysUserById(@PathVariable String id) {
        return sysUserService.deleteSysUserById(id);
    }

    @PutMapping("/update")
    public Result<String> updateSysUser(@RequestBody SysUserVO sysUserVO) {
        return sysUserService.updateSysUser(sysUserVO);
    }

    @GetMapping("/updateStatus/{id}/{status}")
    public Result<String> updateStatus(@PathVariable String id, @PathVariable Integer status) {
        return sysUserService.updateStatus(id, status);
    }

    @GetMapping("/loadByUsername")
    public Result<SysMenuSecurityDTO> loadByUsername(@RequestParam("name") String username) {
        return sysUserService.loadByUsername(username);
    }

}
