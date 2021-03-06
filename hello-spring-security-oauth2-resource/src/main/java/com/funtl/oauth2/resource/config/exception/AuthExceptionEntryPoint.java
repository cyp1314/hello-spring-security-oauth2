package com.funtl.oauth2.resource.config.exception;

import com.funtl.oauth2.resource.constant.ResponseCodeConstant;
import com.funtl.oauth2.resource.constant.ResponseMessageConstant;
import com.funtl.oauth2.resource.constant.ResponseStatusCodeConstant;
import com.funtl.oauth2.resource.utils.ResultJsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint
{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        Map<String, Object> map = new HashMap<String, Object>();
        Throwable cause = authException.getCause();

        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            if(cause instanceof InvalidTokenException) {
                response.getWriter().write(ResultJsonUtil.build(
                        ResponseCodeConstant.REQUEST_FAILED,
                        ResponseStatusCodeConstant.OAUTH_TOKEN_FAILURE,
                        ResponseMessageConstant.OAUTH_TOKEN_ILLEGAL
                ));
            }else{
                response.getWriter().write(ResultJsonUtil.build(
                        ResponseCodeConstant.REQUEST_FAILED,
                        ResponseStatusCodeConstant.OAUTH_TOKEN_MISSING,
                        ResponseMessageConstant.OAUTH_TOKEN_MISSING
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
