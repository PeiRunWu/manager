package com.caroLe.manager.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.caroLe.manager.auth.filter.ManagerAuthenticationFilter;
import com.caroLe.manager.auth.handle.ManagerAuthenticationFailureHandler;
import com.caroLe.manager.auth.handle.ManagerAuthenticationSuccessHandler;
import com.caroLe.manager.auth.handle.ManagerLogoutSuccessHandler;
import com.caroLe.manager.auth.serivce.SecurityAuthService;

/**
 * @author CaroLe
 * @Date 2023/4/20 18:15
 * @Description
 */
@Configuration
@EnableWebSecurity
public class ManagerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityAuthService securityAuthService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new ManagerAuthenticationSuccessHandler(clientDetailsService, authorizationServerTokenServices,
            stringRedisTemplate);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new ManagerAuthenticationFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new ManagerLogoutSuccessHandler(stringRedisTemplate);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 设置用户登入和失败处理器
        ManagerAuthenticationFilter managerAuthenticationFilter =
            new ManagerAuthenticationFilter(authenticationManager());
        managerAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        managerAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        // 配置退出处理器
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler()).and()
            .addFilterAt(managerAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // 配置放行url
        http.authorizeRequests().antMatchers("/oauth/**", "/login", "/rsa/publicKey").permitAll().anyRequest()
            .authenticated();
        http.csrf().disable().cors();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityAuthService).passwordEncoder(passwordEncoder());
    }

}
