package com.funtl.oauth2.server.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 配置认证服务器(基于内存)
 */
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerInMemoryConfiguration extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        // 配置客户端
//        clients
//                // 使用内存设置
//                .inMemory()
//                // client_id
//                .withClient("client")
//                // client_secret
//                .secret(passwordEncoder.encode("secret"))
//                // 授权类型
//                .authorizedGrantTypes("authorization_code")
//                // 授权范围
//                .scopes("app")
//                // 注册回调地址
//                .redirectUris("https://www.baidu.com");
//    }
//}
