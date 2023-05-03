package com.caroLe.manager.repository.dao.system;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caroLe.manager.repository.po.system.SysMenu;



/**
 * @author CaroLe
 * @Date 2023/4/20 14:47
 * @Description
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenu> {}
