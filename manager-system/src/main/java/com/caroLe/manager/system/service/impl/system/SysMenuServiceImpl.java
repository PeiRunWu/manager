package com.caroLe.manager.system.service.impl.system;

import static com.caroLe.manager.common.context.BaseContext.EMPTY_STRING;
import static com.caroLe.manager.common.context.BaseContext.ZERO;
import static com.caroLe.manager.common.context.RedisContext.ROLE_PATH_PREFIX;
import static com.caroLe.manager.common.context.RequestContext.AUTHORIZATION;
import static com.caroLe.manager.common.context.RequestContext.BEARER;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caroLe.manager.common.enums.MenuEnum;
import com.caroLe.manager.common.exception.BaseException;
import com.caroLe.manager.common.exception.DataException;
import com.caroLe.manager.common.exception.UserNameNotFound;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.ErrorType;
import com.caroLe.manager.common.type.SuccessType;
import com.caroLe.manager.repository.dao.system.SysMenuDao;
import com.caroLe.manager.repository.dto.system.AssignAuthMenuDTO;
import com.caroLe.manager.repository.dto.system.DataNodeDTO;
import com.caroLe.manager.repository.dto.system.MenuItemDTO;
import com.caroLe.manager.repository.po.system.SysMenu;
import com.caroLe.manager.repository.po.system.SysRole;
import com.caroLe.manager.repository.po.system.SysRoleMenu;
import com.caroLe.manager.repository.po.system.SysUserRole;
import com.caroLe.manager.repository.vo.system.AssignMenuVO;
import com.caroLe.manager.repository.vo.system.CommonVO;
import com.caroLe.manager.system.service.system.SysMenuService;
import com.caroLe.manager.system.service.system.SysRoleMenuService;
import com.caroLe.manager.system.service.system.SysRoleService;
import com.caroLe.manager.system.service.system.SysUserRoleService;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:58
 * @Description
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 查询菜单
     *
     * @param commonVO 搜索条件
     * @return 分页信息
     */
    @Override
    public Page<SysMenu> getPageList(CommonVO commonVO) {
        Page<SysMenu> page = new Page<>(commonVO.getCurrent(), commonVO.getPageSize());
        LambdaQueryWrapper<SysMenu> sysMenuQueryWrapper = new LambdaQueryWrapper<>();
        sysMenuQueryWrapper.eq(ObjectUtil.isNotEmpty(commonVO.getSearchObj()), SysMenu::getParentId,
            commonVO.getSearchObj());
        return baseMapper.selectPage(page, sysMenuQueryWrapper);
    }

    /**
     * 修改菜单是否显示
     *
     * @param id 菜单Id
     * @param hidden 是否显示
     * @return Result
     */
    @Override
    public Result<String> updateHidden(String id, Integer hidden) {
        SysMenu sysMenu = baseMapper.selectById(id);
        if (ObjectUtil.isEmpty(sysMenu)) {
            throw new DataException(ErrorType.UPDATE_DATE);
        }
        sysMenu.setHidden(hidden);
        baseMapper.updateById(sysMenu);
        return Result.success(SuccessType.SUCCESS);
    }

    /**
     * 获取所有的菜单
     *
     * @return 获取所有的菜单
     */
    @Override
    public Result<List<SysMenu>> getAllMenuItems() {
        List<SysMenu> sysMenuList =
            baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getType, MenuEnum.MENU.getCode()));
        return Result.success(sysMenuList, SuccessType.SUCCESS);
    }

    /**
     * 获取所有目录
     *
     * @return 获取所有目录
     */
    @Override
    public Result<List<SysMenu>> getAllTableItems() {
        List<SysMenu> sysMenuList =
            baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getType, MenuEnum.DIRECTORY.getCode()));
        return Result.success(sysMenuList, SuccessType.SUCCESS);
    }

    /**
     * 删除菜单
     *
     * @param id 菜单Id
     */
    @Override
    @Transactional(rollbackFor = BaseException.class)
    public void removeMenu(String id) {
        LambdaQueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new LambdaQueryWrapper<>();
        sysRoleMenuQueryWrapper.eq(SysRoleMenu::getMenuId, id);
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuService.list(sysRoleMenuQueryWrapper);
        if (CollectionUtils.isNotEmpty(sysRoleMenuList)) {
            throw new DataException(ErrorType.MENU_USER_BIND);
        }
        LambdaQueryWrapper<SysMenu> sysMenuQueryWrapper = new LambdaQueryWrapper<>();
        sysMenuQueryWrapper.eq(SysMenu::getParentId, id);
        List<SysMenu> sysMenus = baseMapper.selectList(sysMenuQueryWrapper);
        if (CollectionUtils.isNotEmpty(sysMenus)) {
            throw new DataException(ErrorType.NODE_ERROR);
        }
        baseMapper.deleteById(id);
    }

    /**
     * 获取所有资源
     *
     * @param commonVO 搜索条件
     * @return 获取所有资源
     */
    @Override
    public Result<Page<SysMenu>> getResourcePageList(CommonVO commonVO) {
        Page<SysMenu> menuPage = new Page<>(commonVO.getCurrent(), commonVO.getPageSize());
        LambdaQueryWrapper<SysMenu> sysMenuQueryWrapper = new LambdaQueryWrapper<>();
        sysMenuQueryWrapper.eq(SysMenu::getType, MenuEnum.BUTTON.getCode());
        sysMenuQueryWrapper.like(StrUtil.isNotBlank(commonVO.getName()), SysMenu::getName, commonVO.getName());
        sysMenuQueryWrapper.like(StrUtil.isNotBlank(commonVO.getPath()), SysMenu::getPath, commonVO.getPath());
        sysMenuQueryWrapper.like(StrUtil.isNotBlank(commonVO.getParentId()), SysMenu::getParentId,
            commonVO.getParentId());
        Page<SysMenu> page = baseMapper.selectPage(menuPage, sysMenuQueryWrapper);
        return Result.success(page, SuccessType.SUCCESS);
    }

    /**
     * 根据角色获取菜单
     *
     * @param roleId 角色Id
     * @return 菜单信息
     */
    @Override
    public AssignAuthMenuDTO findSysMenuByRoleId(String roleId) {
        List<SysMenu> allSysMenuList = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>());
        List<SysRoleMenu> sysRoleMenuList =
            sysRoleMenuService.list(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        List<String> menuIdList = sysRoleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        AssignAuthMenuDTO assignAuthDTO = new AssignAuthMenuDTO();
        assignAuthDTO.setMenuIds(menuIdList);
        List<DataNodeDTO> dataNodeDTOList = listDataNode(allSysMenuList);
        assignAuthDTO.setDataNode(dataNodeDTOList);
        return assignAuthDTO;
    }

    /**
     * 更新菜单或资源
     *
     * @param sysMenu 菜单信息
     * @return 更新菜单或资源
     */
    @Override
    public Result<String> updateMenuById(SysMenu sysMenu) {
        if (MenuEnum.BUTTON.getCode() == sysMenu.getType()) {
            // 先查出当前资源
            SysMenu menu = baseMapper.selectById(sysMenu.getId());
            // 比较资源路径
            if (!StrUtil.equals(sysMenu.getPath(), menu.getPath())) {
                throw new DataException(ErrorType.BINDING_PERMISSIONS);
            }
        }
        baseMapper.updateById(sysMenu);
        return Result.success(SuccessType.SUCCESS);
    }

    /**
     * 分配权限
     *
     * @param assignMenuVo 权限VO
     */
    @Override
    @Transactional(rollbackFor = BaseException.class)
    public void doAssign(AssignMenuVO assignMenuVo) {
        String roleId = assignMenuVo.getRoleId();
        SysRole sysRole = sysRoleService.getById(roleId);
        // 删除redis 数据
        stringRedisTemplate.delete(ROLE_PATH_PREFIX + sysRole.getRoleCode());
        sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        List<String> pathList = new ArrayList<>();
        for (String menuId : assignMenuVo.getMenuIdList()) {
            SysMenu sysMenu = baseMapper.selectById(menuId);
            if (ObjectUtil.isEmpty(sysMenu)) {
                throw new DataException(ErrorType.ARGUMENT_VALID_ERROR);
            }
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenuService.save(sysRoleMenu);
            if (StrUtil.isNotEmpty(sysMenu.getPath())) {
                pathList.add(sysMenu.getPath());
            }
        }
        // redis 直接保存角色对应的path
        stringRedisTemplate.opsForValue().set(ROLE_PATH_PREFIX + sysRole.getRoleCode(), JSON.toJSONString(pathList));
    }

    /**
     * 获取当前用户菜单权限
     *
     * @return 获取当前用户菜单权限
     */
    @Override
    public Result<List<MenuItemDTO>> getUserMenu() {
        HttpServletRequest request =
            ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        String authorization = request.getHeader(AUTHORIZATION);
        if (ObjectUtil.isEmpty(authorization)) {
            return Result.success(SuccessType.SUCCESS);
        }
        String token = authorization.replace(BEARER, EMPTY_STRING).trim();
        String userId = stringRedisTemplate.opsForValue().get(token);
        if (ObjectUtil.isEmpty(userId)) {
            throw new UserNameNotFound(ErrorType.LOGIN_AUTH);
        }
        List<SysUserRole> sysUserRoles =
            sysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
        // 获取当前用户角色
        List<String> roleIdList = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        List<SysRoleMenu> sysRoleMenuList =
            sysRoleMenuService.list(new LambdaQueryWrapper<SysRoleMenu>().in(SysRoleMenu::getRoleId, roleIdList));
        if (CollectionUtils.isEmpty(sysRoleMenuList)) {
            return Result.success(SuccessType.SUCCESS);
        }
        // 获取当前用户menu
        List<String> menuIdList = sysRoleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        List<SysMenu> allSysMenuList = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>());
        allSysMenuList.removeIf(sysMenu -> !menuIdList.contains(sysMenu.getId()));
        return Result.success(listMenuItem(allSysMenuList), SuccessType.SUCCESS);
    }

    /**
     * 遍历显示菜单栏
     */
    private List<MenuItemDTO> listMenuItem(List<SysMenu> allMenu) {
        return allMenu.stream().filter(sysMenu -> ZERO.equals(sysMenu.getParentId())).map(sysMenu -> {
            MenuItemDTO menuItemDTO = new MenuItemDTO();
            menuItemDTO.setPath(sysMenu.getPath());
            menuItemDTO.setSortValue(sysMenu.getSortValue());
            if (ObjectUtil.isNotEmpty(sysMenu.getHidden())) {
                menuItemDTO.setHideInMenu(BooleanUtil.toBoolean(sysMenu.getHidden().toString()));
            }
            menuItemDTO.setName(sysMenu.getName());
            menuItemDTO.setIcon(sysMenu.getIcon());
            menuItemDTO.setId(sysMenu.getId());
            return menuItemDTO;
        }).peek(menuItemDTO -> menuItemDTO.setRoutes(getChildrenMenuItem(menuItemDTO, allMenu)))
            .sorted((menu1, menu2) -> menu1.getSortValue() - menu2.getSortValue()).collect(Collectors.toList());
    }

    private List<MenuItemDTO> getChildrenMenuItem(MenuItemDTO root, List<SysMenu> allMenu) {
        return allMenu.stream().filter(menu -> menu.getParentId().equals(root.getId())).map(sysMenu -> {
            MenuItemDTO menuItemDTO = new MenuItemDTO();
            menuItemDTO.setPath(sysMenu.getPath());
            menuItemDTO.setSortValue(sysMenu.getSortValue());
            if (ObjectUtil.isNotEmpty(sysMenu.getHidden())) {
                menuItemDTO.setHideInMenu(BooleanUtil.toBoolean(sysMenu.getHidden().toString()));
            }
            menuItemDTO.setName(sysMenu.getName());
            menuItemDTO.setIcon(sysMenu.getIcon());
            menuItemDTO.setId(sysMenu.getId());
            return menuItemDTO;
        }).sorted((menu1, menu2) -> menu1.getSortValue() - menu2.getSortValue()).collect(Collectors.toList());
    }

    /**
     * 授权菜单管理
     */
    private List<DataNodeDTO> listDataNode(List<SysMenu> allMenu) {
        return allMenu.stream().filter(sysMenu -> ZERO.equals(sysMenu.getParentId())).map(sysMenu -> {
            DataNodeDTO dataNodeDTO = new DataNodeDTO();
            dataNodeDTO.setKey(sysMenu.getId());
            dataNodeDTO.setSortValue(sysMenu.getSortValue());
            dataNodeDTO.setTitle(sysMenu.getName());
            return dataNodeDTO;
        }).peek(dataNode -> dataNode.setChildren(getChildrenDataNode(dataNode, allMenu)))
            .sorted((menu1, menu2) -> menu1.getSortValue() - menu2.getSortValue()).collect(Collectors.toList());
    }

    private List<DataNodeDTO> getChildrenDataNode(DataNodeDTO root, List<SysMenu> allMenu) {
        return allMenu.stream().filter(sysMenu -> sysMenu.getParentId().equals(root.getKey())).map(sysMenu -> {
            DataNodeDTO dataNodeDTO = new DataNodeDTO();
            dataNodeDTO.setKey(sysMenu.getId());
            dataNodeDTO.setSortValue(sysMenu.getSortValue());
            dataNodeDTO.setTitle(sysMenu.getName());
            return dataNodeDTO;
        }).peek(dataNode -> dataNode.setChildren(getChildrenDataNode(dataNode, allMenu)))
            .sorted((menu1, menu2) -> menu1.getSortValue() - menu2.getSortValue()).collect(Collectors.toList());
    }
}
