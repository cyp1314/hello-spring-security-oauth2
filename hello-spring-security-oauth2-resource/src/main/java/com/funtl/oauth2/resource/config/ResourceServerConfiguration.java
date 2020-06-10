package com.funtl.oauth2.resource.config;

import com.funtl.oauth2.resource.config.exception.AuthExceptionEntryPoint;
import com.funtl.oauth2.resource.config.exception.CustomAccessDeniedHandler;
import com.funtl.oauth2.resource.config.exception.CustomTokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    CustomTokenExtractor customTokenExtractor;

    @Autowired
    AuthExceptionEntryPoint authExceptionEntryPoint;

    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenExtractor(customTokenExtractor);
        resources.authenticationEntryPoint(authExceptionEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .and()
                // Session 创建策略
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").hasAuthority("SystemContent")
                .antMatchers("/view/**").hasAuthority("SystemContentView")
                .antMatchers("/insert/**").hasAuthority("SystemContentInsert")
                .antMatchers("/update/**").hasAuthority("SystemContentUpdate")
                .antMatchers("/delete/**").hasAuthority("SystemContentDelete");
    }

}