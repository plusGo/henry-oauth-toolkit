package com.henry.home.oauth.starter;

import com.henry.home.oauth.configuration.OAuthProperties;
import com.henry.home.oauth.feign.OAuthClient;
import com.henry.home.oauth.service.OAuthProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class OAuthConfiguration {

    @Bean
    @ConditionalOnMissingBean(OAuthProperties.class)
    @ConfigurationProperties(prefix = "henry.oauth.client")
    public OAuthProperties oAuthProperties() {
        return new OAuthProperties();
    }

    @Bean
    @ConditionalOnMissingBean(OAuthProvider.class)
    public OAuthProvider oAuthProvider(final OAuthClient oAuthClient,
                                       final OAuthProperties oAuthProperties) {
        return new OAuthProvider(oAuthClient, oAuthProperties);
    }

}
