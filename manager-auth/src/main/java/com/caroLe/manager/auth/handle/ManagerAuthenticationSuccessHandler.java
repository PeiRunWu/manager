package com.caroLe.manager.auth.handle;

import com.caroLe.manager.auth.domain.SecurityUser;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.SuccessType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.caroLe.manager.common.context.BaseContext.*;
import static com.caroLe.manager.common.context.RequestContext.ACCESS_TOKEN;
import static com.caroLe.manager.common.context.RequestContext.TOKEN_TYPE;

/**
 * @author CaroLe
 * @Date 2023/4/21 16:53
 * @Description 登入成功处理器
 */
@Component
public class ManagerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ClientDetailsService clientDetailsService;

    private final AuthorizationServerTokenServices authorizationServerTokenServices;

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public ManagerAuthenticationSuccessHandler(ClientDetailsService clientDetailsService,
        AuthorizationServerTokenServices authorizationServerTokenServices, StringRedisTemplate stringRedisTemplate) {
        this.clientDetailsService = clientDetailsService;
        this.authorizationServerTokenServices = authorizationServerTokenServices;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        SecurityUser securityUser = (SecurityUser)authentication.getPrincipal();
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(securityUser.getClientId());

        Map<String, String> parameters = new HashMap<>(4);
        parameters.put(GRANT_TYPE, clientDetails.getAuthorizedGrantTypes().toString());
        parameters.put(USERNAME, authentication.getName());
        parameters.put(PASSWORD, (String)authentication.getCredentials());
        parameters.put(SCOPE, clientDetails.getScope().toString());
        TokenRequest tokenRequest = new TokenRequest(parameters, clientDetails.getClientId(), clientDetails.getScope(),
            clientDetails.getAuthorizedGrantTypes().toString());
        OAuth2Authentication oauth2Authentication =
            new OAuth2Authentication(tokenRequest.createOAuth2Request(clientDetails), authentication);
        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oauth2Authentication);

        Map<String, Object> responseBody = new HashMap<>(2);
        responseBody.put(ACCESS_TOKEN, token.getValue());
        responseBody.put(TOKEN_TYPE, token.getTokenType());
        stringRedisTemplate.opsForValue().set(token.getValue(), securityUser.getId(), 1, TimeUnit.DAYS);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), Result.success(responseBody, SuccessType.SUCCESS));
    }
}
