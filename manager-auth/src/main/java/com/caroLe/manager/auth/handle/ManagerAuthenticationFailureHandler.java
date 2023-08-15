package com.caroLe.manager.auth.handle;

import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.ErrorType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CaroLe
 * @Date 2023/4/22 13:12
 * @Description 登入失败处理器
 */
@Slf4j
public class ManagerAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {
        log.error("ManagerAuthenticationFailureHandler:" + exception.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), Result.failed(ErrorType.USER_NO_ACCESS));
    }
}
