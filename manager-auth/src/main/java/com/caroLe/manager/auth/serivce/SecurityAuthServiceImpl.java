package com.caroLe.manager.auth.serivce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.caroLe.manager.auth.domain.SecurityUser;
import com.caroLe.manager.auth.feign.ManagerSystemService;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.SuccessType;
import com.caroLe.manager.repository.dto.system.SysMenuSecurityDTO;

/**
 * @author CaroLe
 * @Date 2023/4/21 14:58
 * @Description
 */
@Service
public class SecurityAuthServiceImpl implements UserDetailsService {

    @Autowired
    private ManagerSystemService managerSystemService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<SysMenuSecurityDTO> result = managerSystemService.loadByUsername(username);
        if (!SuccessType.SUCCESS.getCode().equals(result.getCode())) {
            return null;
        }
        SysMenuSecurityDTO sysMenuSecurityDTO = result.getData();
        SecurityUser securityUser = new SecurityUser(sysMenuSecurityDTO);
        List<String> roleCodeList = sysMenuSecurityDTO.getRoleCodeList();
        List<String> permissionValueList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roleCodeList)) {
            permissionValueList.addAll(roleCodeList);
        }
        securityUser.setPermissionValueList(permissionValueList);
        return securityUser;
    }
}
