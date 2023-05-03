package com.caroLe.manager.auth.handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.SuccessType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author CaroLe
 * @Date 2023/4/21 17:09
 * @Description 退出成功处理器
 */
@Component
public class ManagerLogoutSuccessHandler implements LogoutSuccessHandler {

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public ManagerLogoutSuccessHandler(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {
        String token = request.getHeader("Authorization").replace("Bearer", "").trim();
        stringRedisTemplate.delete(token);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), Result.success(null, SuccessType.SUCCESS));
    }
}
