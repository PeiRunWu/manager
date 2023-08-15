package com.caroLe.manager.auth.domain;

import cn.hutool.core.util.StrUtil;
import com.caroLe.manager.repository.dto.system.SysMenuSecurityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author CaroLe
 * @Date 2023/4/21 14:49
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = -1L;

    private String id;

    private String username;

    private String password;

    private Integer status;

    private String clientId;

    private List<String> permissionValueList;

    public SecurityUser(SysMenuSecurityDTO sysMenuSecurityDTO) {
        this.username = sysMenuSecurityDTO.getUsername();
        this.password = sysMenuSecurityDTO.getPassword();
        this.status = sysMenuSecurityDTO.getStatus();
        this.clientId = sysMenuSecurityDTO.getClientId();
        this.id = sysMenuSecurityDTO.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String permissionValue : this.permissionValueList) {
            if (StrUtil.isEmpty(permissionValue)) {
                continue;
            }
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == 1;
    }
}
