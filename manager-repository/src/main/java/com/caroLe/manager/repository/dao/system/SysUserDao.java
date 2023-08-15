package com.caroLe.manager.repository.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caroLe.manager.repository.dto.system.SysMenuSecurityDTO;
import com.caroLe.manager.repository.dto.system.SysUserDTO;
import com.caroLe.manager.repository.po.system.SysUser;
import com.caroLe.manager.repository.vo.system.CommonVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param commonVO 分页查询条件
     * @return 分页信息
     */
    List<SysUserDTO> listPage(@Param("commonVO") CommonVO commonVO);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysMenuSecurityDTO loadByUsername(@Param("username") String username);
}