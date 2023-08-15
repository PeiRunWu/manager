package com.caroLe.manager.system.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.system.SysMenuSecurityDTO;
import com.caroLe.manager.repository.dto.system.SysUserDTO;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.repository.vo.system.SysUserVO;
import com.caroLe.manager.system.service.system.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CaroLe
 * @Date 2023/2/28 20:44
 * @Description
 */
@RestController
@Api(tags = "后台用户列表管理")
@RequestMapping("/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/register")
    @ApiOperation(value = "注册用户")
    public Result<String> registerUser(@RequestBody SysUserVO sysUserVO) {
        return sysUserService.registerUser(sysUserVO);
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取当前登入用户信息")
    public Result<SysUserDTO> getUserInfo() {
        return sysUserService.getUserInfo();
    }

    @GetMapping("/listPage")
    @ApiOperation(value = "获取用户信息分页查询")
    public Result<Page<SysUserDTO>> listPage(CommonVO commonVO) {
        return sysUserService.listPage(commonVO);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除用户")
    public Result<String> deleteSysUserById(@PathVariable String id) {
        return sysUserService.deleteSysUserById(id);
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新用户")
    public Result<String> updateSysUser(@RequestBody SysUserVO sysUserVO) {
        return sysUserService.updateSysUser(sysUserVO);
    }

    @GetMapping("/updateStatus/{id}/{status}")
    @ApiOperation(value = "更新用户状态")
    public Result<String> updateStatus(@PathVariable String id, @PathVariable Integer status) {
        return sysUserService.updateStatus(id, status);
    }

    @GetMapping("/loadByUsername")
    @ApiOperation(value = "根据用户名获取用户信息")
    public Result<SysMenuSecurityDTO> loadByUsername(@RequestParam("name") String username) {
        return sysUserService.loadByUsername(username);
    }

}
