package com.caroLe.manager.auth.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.caroLe.manager.auth.domain.SecurityUser;

/**
 * @author CaroLe
 * @Date 2023/4/21 21:56
 * @Description
 */
@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (accessToken instanceof DefaultOAuth2AccessToken) {
            SecurityUser securityUser = (SecurityUser)authentication.getPrincipal();
            DefaultOAuth2AccessToken token = ((DefaultOAuth2AccessToken)accessToken);
            Map<String, Object> additionalInformation = new HashMap<>(1);
            additionalInformation.put("id", securityUser.getId());
            additionalInformation.put("roleCode", securityUser.getPermissionValueList());
            token.setAdditionalInformation(additionalInformation);
            return token;
        }
        return accessToken;
    }
}
