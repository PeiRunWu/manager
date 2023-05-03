package com.caroLe.manager.repository.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caroLe.manager.repository.dto.system.SysMenuSecurityDTO;
import com.caroLe.manager.repository.dto.system.SysUserDTO;
import com.caroLe.manager.repository.po.system.SysUser;
import com.caroLe.manager.repository.vo.system.CommonVO;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:47
 * @Description
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {
    /**
     * 分页查询
     *
     * @param commonVO
     * @return
     */
    List<SysUserDTO> listPage(@Param("commonVO") CommonVO commonVO);

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    SysMenuSecurityDTO loadByUsername(@Param("username") String username);
}