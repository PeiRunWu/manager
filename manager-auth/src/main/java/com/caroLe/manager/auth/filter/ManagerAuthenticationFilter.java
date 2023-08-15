package com.caroLe.manager.auth.filter;

import cn.hutool.core.util.StrUtil;
import com.caroLe.manager.auth.domain.SecurityUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CaroLe
 * @Date 2023/4/21 13:59
 * @Description
 */
public class ManagerAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationManager authenticationManager;

    public ManagerAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException, IOException, ServletException {
        SecurityUser securityUser = new ObjectMapper().readValue(request.getInputStream(), SecurityUser.class);
        if (StrUtil.isBlank(securityUser.getPassword()) || StrUtil.isBlank(securityUser.getUsername())) {
            throw new UsernameNotFoundException("用户名或密码未输入");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(securityUser.getUsername(), securityUser.getPassword());
        this.setDetails(request, usernamePasswordAuthenticationToken);
        return this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
