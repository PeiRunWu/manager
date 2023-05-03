package com.caroLe.manager.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.caroLe.manager.gateway.authorization.AuthorizationManager;
import com.caroLe.manager.gateway.handler.ManagerAccessDeniedHandler;
import com.caroLe.manager.gateway.handler.ManagerAuthenticationEntryPoint;

import reactor.core.publisher.Mono;

/**
 * @author CaroLe
 * @Date 2023/4/21 23:33
 * @Description
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private AllowedPathConfig allowedPathConfig;

    @Autowired
    private AuthorizationManager authorizationManager;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.oauth2ResourceServer().jwt().jwkSetUri("http://localhost:9000/auth/rsa/publicKey")
            .jwtAuthenticationConverter(jwtAuthenticationConverter());

        http.oauth2ResourceServer().authenticationEntryPoint(new ManagerAuthenticationEntryPoint());

        http.authorizeExchange().pathMatchers(allowedPathConfig.getPaths().toArray(new String[0])).permitAll()
            .anyExchange().access(authorizationManager);

        http.exceptionHandling().authenticationEntryPoint(new ManagerAuthenticationEntryPoint())
            .accessDeniedHandler(new ManagerAccessDeniedHandler()).and();

        http.oauth2ResourceServer().authenticationEntryPoint(new ManagerAuthenticationEntryPoint());
        http.csrf().disable().cors();
        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
