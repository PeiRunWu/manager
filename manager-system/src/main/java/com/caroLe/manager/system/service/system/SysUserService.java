package com.caroLe.manager.system.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.system.SysMenuSecurityDTO;
import com.caroLe.manager.repository.dto.system.SysUserDTO;
import com.caroLe.manager.repository.po.system.SysUser;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.repository.vo.system.SysUserVO;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:57
 * @Description
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 用户注册
     * 
     * @param sysUserVO 用户信息
     * @return 用户注册
     */
    Result<String> registerUser(SysUserVO sysUserVO);

    /**
     * 获取当前登入用户信息
     *
     * @return 获取当前登入用户信息
     */
    Result<SysUserDTO> getUserInfo();

    /**
     * 分页查询
     *
     * @param commonVO 搜索条件
     * @return 分页查询
     */
    Result<Page<SysUserDTO>> listPage(CommonVO commonVO);

    /**
     * 删除用户
     *
     * @param id 用户Id
     * @return Result
     */
    Result<String> deleteSysUserById(String id);

    /**
     * 更新用户信息
     *
     * @param sysUserVO 用户信息
     * @return Result
     */
    Result<String> updateSysUser(SysUserVO sysUserVO);

    /**
     * 更新状态
     *
     * @param id 用户Id
     * @param status 状态
     * @return Result
     */
    Result<String> updateStatus(String id, Integer status);

    /**
     * 根据用户名获取用户信息
     * 
     * @param username 用户名
     * @return 获取用户信息
     */
    Result<SysMenuSecurityDTO> loadByUsername(String username);
}
