package com.caroLe.manager.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caroLe.manager.repository.dao.system.SysUserRoleDao;
import com.caroLe.manager.repository.po.system.SysUserRole;
import com.caroLe.manager.system.service.SysUserRoleService;

/**
 * @author CaroLe
 * @Date 2023/3/7 10:33
 * @Description
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {
}
