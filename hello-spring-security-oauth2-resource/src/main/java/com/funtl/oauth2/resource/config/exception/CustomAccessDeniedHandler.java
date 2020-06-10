package com.funtl.oauth2.resource.config.exception;

import com.funtl.oauth2.resource.constant.ResponseCodeConstant;
import com.funtl.oauth2.resource.constant.ResponseMessageConstant;
import com.funtl.oauth2.resource.constant.ResponseStatusCodeConstant;
import com.funtl.oauth2.resource.utils.ResultJsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            response.getWriter().write(ResultJsonUtil.build(
                    ResponseCodeConstant.REQUEST_FAILED,
                    ResponseStatusCodeConstant.OAUTH_TOKEN_DENIED,
                    ResponseMessageConstant.OAUTH_TOKEN_DENIED
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
