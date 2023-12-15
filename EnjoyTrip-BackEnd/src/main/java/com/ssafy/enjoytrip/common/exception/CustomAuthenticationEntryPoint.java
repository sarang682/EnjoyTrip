package com.ssafy.enjoytrip.common.exception;

import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(ExceptionStatus.INVALID_TOKEN.getStatus());
        response.getWriter().write("{"+
                "\"message\":" + "\"" + "token is null or not invalid" +"\"" + "}");
    }
}
