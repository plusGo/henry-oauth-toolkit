package com.henry.home.oauth.starter.configuration;

import lombok.Data;

@Data
public class OAuthProperties {
    /**
     * 认证中心服务根地址
     */
    private String authServerUri;

    /**
     * 应用ID
     */
    private String clientId;

    /**
     * 应用秘钥
     */
    private String clientSecret;

    /**
     *  认证中心的公钥
     */
    private String rsaPublicKey;

    /**
     *  登录成功后的回跳地址
     */
    private String redirectUri;

}
