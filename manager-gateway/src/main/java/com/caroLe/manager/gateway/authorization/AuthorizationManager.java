package com.caroLe.manager.gateway.authorization;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.caroLe.manager.gateway.config.AllowedPathConfig;
import com.caroLe.manager.repository.dto.system.UserDTO;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.caroLe.manager.common.context.BaseContext.PATTERN_MANAGER;
import static com.caroLe.manager.common.context.RedisContext.ROLE_PATH_PREFIX;
import static com.caroLe.manager.common.context.RequestContext.AUTHORIZATION;
import static com.caroLe.manager.common.context.RequestContext.BEARER;

/**
 * @author CaroLe
 * @Date 2023/4/22 15:59
 * @Description
 */
@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AllowedPathConfig allowedPathConfig;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        Matcher matcher = Pattern.compile(PATTERN_MANAGER).matcher(uri.getPath());
        String patternPath = "";
        if (matcher.find()) {
            patternPath = "/" + matcher.group(1);
        }
        // 如果在白名单之内直接放行
        List<String> allowedPatch = allowedPathConfig.getPaths();
        for (String allow : allowedPatch) {
            if (pathMatcher.match(allow, patternPath)) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }

        // 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }

        List<String> allowPattern = new ArrayList<>();
        try {
            String token = request.getHeaders().getFirst(AUTHORIZATION);
            if (StrUtil.isBlank(token)) {
                return Mono.just(new AuthorizationDecision(false));
            }
            String realToken = token.replace(BEARER, "");
            String userId = stringRedisTemplate.opsForValue().get(realToken);
            if (StrUtil.isBlank(userId)) {
                return Mono.just(new AuthorizationDecision(false));
            }
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            UserDTO userDTO = JSONObject.parseObject(userStr, UserDTO.class);
            List<String> authorities = userDTO.getAuthorities();

            if (CollectionUtils.isEmpty(authorities)) {
                return Mono.just(new AuthorizationDecision(false));
            }
            // 从redis 读取当前角色配置的路径
            List<String> authorityPath = new ArrayList<>();
            for (String authority : authorities) {
                String path = stringRedisTemplate.opsForValue().get(ROLE_PATH_PREFIX + authority);
                List<String> pathList = JSONObject.parseObject(path, new TypeReference<List<String>>() {});
                if (CollectionUtil.isNotEmpty(pathList)) {
                    authorityPath.addAll(pathList);
                }
            }
            if (CollectionUtil.isEmpty(authorityPath)) {
                return Mono.just(new AuthorizationDecision(false));
            }
            // 比较当前路径是否在角色里面
            boolean isPattern = false;
            for (String pattern : authorityPath) {
                if (pathMatcher.match(pattern, patternPath)) {
                    isPattern = true;
                    break;
                }
            }
            // isPattern 为true ,路径匹配上
            if (isPattern) {
                allowPattern.addAll(authorities);
            } else {
                return Mono.just(new AuthorizationDecision(false));
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return Mono.just(new AuthorizationDecision(false));
        }

        return mono.filter(Authentication::isAuthenticated).flatMapIterable(Authentication::getAuthorities)
            .map(GrantedAuthority::getAuthority).any(allowPattern::contains).map(AuthorizationDecision::new)
            .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
