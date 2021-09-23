package com.henry.home.oauth.starter;

import com.henry.home.oauth.starter.configuration.OAuthProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class OAuthConfiguration {

    @Bean
    @ConditionalOnMissingBean(OAuth2ClientProperties.class)
    @ConfigurationProperties(prefix = "henry.oauth.client")
    public OAuthProperties securityProperties() {
        return new OAuthProperties();
    }

}
