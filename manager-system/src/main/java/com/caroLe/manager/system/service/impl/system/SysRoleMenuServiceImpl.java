package com.caroLe.manager.system.service.impl.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caroLe.manager.repository.dao.system.SysRoleMenuDao;
import com.caroLe.manager.repository.po.system.SysRoleMenu;
import com.caroLe.manager.system.service.system.SysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:58
 * @Description
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {}

