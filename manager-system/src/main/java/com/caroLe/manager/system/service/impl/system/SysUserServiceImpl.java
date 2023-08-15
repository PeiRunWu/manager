package com.caroLe.manager.system.service.impl.system;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caroLe.manager.common.enums.LoginEnum;
import com.caroLe.manager.common.exception.BaseException;
import com.caroLe.manager.common.exception.DataException;
import com.caroLe.manager.common.exception.UserNameNotFound;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.ErrorType;
import com.caroLe.manager.common.type.StatusType;
import com.caroLe.manager.common.type.SuccessType;
import com.caroLe.manager.repository.dao.system.SysUserDao;
import com.caroLe.manager.repository.dto.system.SysMenuSecurityDTO;
import com.caroLe.manager.repository.dto.system.SysUserDTO;
import com.caroLe.manager.repository.po.system.SysRole;
import com.caroLe.manager.repository.po.system.SysUser;
import com.caroLe.manager.repository.po.system.SysUserRole;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.repository.vo.system.SysUserVO;
import com.caroLe.manager.system.service.system.SysRoleService;
import com.caroLe.manager.system.service.system.SysUserRoleService;
import com.caroLe.manager.system.service.system.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author CaroLe
 * @Date 2023/2/27 20:43
 * @Description
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取当前登入用户信息
     *
     * @return 获取当前登入用户信息
     */
    @Override
    public Result<SysUserDTO> getUserInfo() {
        HttpServletRequest request =
            ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        String authorization = request.getHeader("Authorization");
        if (ObjectUtil.isEmpty(authorization)) {
            throw new UserNameNotFound(ErrorType.LOGIN_AUTH);
        }
        String token = authorization.replace("Bearer", "").trim();
        String userId = stringRedisTemplate.opsForValue().get(token);
        SysUser sysUser = baseMapper.selectById(userId);
        if (ObjectUtil.isEmpty(sysUser)) {
            throw new UserNameNotFound(ErrorType.LOGIN_AUTH);
        }
        SysUserDTO sysUserDTO = new SysUserDTO();
        BeanUtils.copyProperties(sysUser, sysUserDTO);
        return Result.success(sysUserDTO, SuccessType.SUCCESS);
    }

    /**
     * 分页查询
     *
     * @param commonVO 搜索条件
     * @return 分页查询
     */
    @Override
    public Result<Page<SysUserDTO>> listPage(CommonVO commonVO) {
        Page<SysUserDTO> page = new Page<>(commonVO.getCurrent(), commonVO.getPageSize());
        List<SysUserDTO> sysUserDTOList = baseMapper.listPage(commonVO);
        page.setRecords(sysUserDTOList);
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            page.setTotal(sysUserDTOList.size());
        }
        return Result.success(page, StatusType.SUCCESS);
    }

    /**
     * 删除用户
     *
     * @param id 用户Id
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = BaseException.class)
    public Result<String> deleteSysUserById(String id) {
        int delete = baseMapper.deleteById(id);
        if (delete <= 0) {
            throw new DataException(ErrorType.DELETE_DATE);
        }
        sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, id));
        return Result.success(null, StatusType.SUCCESS);
    }

    /**
     * 更新用户信息
     *
     * @param sysUserVO 用户信息
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = BaseException.class)
    public Result<String> updateSysUser(SysUserVO sysUserVO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        int update = baseMapper.updateById(sysUser);
        if (update <= 0) {
            throw new DataException(ErrorType.UPDATE_DATE);
        }
        return Result.success(null, StatusType.SUCCESS);
    }

    /**
     * 更新状态
     *
     * @param id 用户Id
     * @param status 状态
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = BaseException.class)
    public Result<String> updateStatus(String id, Integer status) {
        SysUser sysUser = baseMapper.selectById(id);
        sysUser.setStatus(status);
        baseMapper.updateById(sysUser);
        return Result.success(SuccessType.SUCCESS);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 获取用户信息
     */
    @Override
    public Result<SysMenuSecurityDTO> loadByUsername(String username) {
        SysMenuSecurityDTO sysMenuSecurityDTO = baseMapper.loadByUsername(username);
        if (ObjectUtil.isEmpty(sysMenuSecurityDTO)) {
            throw new UserNameNotFound(ErrorType.USER_NAME_NOT_EXIT);
        }
        return Result.success(sysMenuSecurityDTO, SuccessType.SUCCESS);
    }

    /**
     * 用户注册
     *
     * @param sysUserVO 用户信息
     * @return 用户注册
     */
    @Override
    @Transactional(rollbackFor = BaseException.class)
    public Result<String> registerUser(SysUserVO sysUserVO) {
        SysUser sysUser =
            baseMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, sysUserVO.getUsername()));
        if (ObjectUtil.isNotEmpty(sysUser)) {
            throw new UserNameNotFound(ErrorType.USER_NAME_EXIT);
        }
        sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        sysUser.setPassword(BCrypt.hashpw(sysUserVO.getPassword()));
        sysUser.setStatus(LoginEnum.ACTIVATE.getCode());
        baseMapper.insert(sysUser);
        // 默认添加user 权限
        SysRole sysRole = sysRoleService.getOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleCode, "user"));
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(sysRole.getId());
        sysUserRole.setUserId(sysUser.getId());
        sysUserRoleService.save(sysUserRole);
        return Result.success(SuccessType.SUCCESS);
    }
}
