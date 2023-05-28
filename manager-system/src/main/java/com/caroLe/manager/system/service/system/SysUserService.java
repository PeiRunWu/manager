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
     * @param sysUserVO
     * @return
     */
    Result<String> registerUser(SysUserVO sysUserVO);

    /**
     * 获取当前登入用户信息
     *
     * @return
     */
    Result<SysUserDTO> getUserInfo();

    /**
     * 分页查询
     *
     * @param commonVO
     * @return
     */
    Result<Page<SysUserDTO>> listPage(CommonVO commonVO);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    Result<String> deleteSysUserById(String id);

    /**
     * 更新用户信息
     *
     * @param sysUserVO
     * @return
     */
    Result<String> updateSysUser(SysUserVO sysUserVO);


    /**
     * 更新状态
     *
     * @param id
     * @param status
     * @return
     */
    Result<String> updateStatus(String id, Integer status);

    /**
     * 根据用户名获取用户信息
     * 
     * @param username
     * @return
     */
    Result<SysMenuSecurityDTO> loadByUsername(String username);
}
