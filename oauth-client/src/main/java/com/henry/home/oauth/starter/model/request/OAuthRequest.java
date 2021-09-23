package com.henry.home.oauth.starter.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OAuthRequest {

    @JsonProperty("grant_type")
    private String grantType;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("redirect_uri")
    private String redirectUri;

    @JsonProperty("refresh_token")
    private String refreshToken;

    private String code;

    @JsonProperty("client_secret")
    private String clientSecret;

    public void setGrant_type(final String grantType) {
        this.grantType = grantType;
    }

    public void setClient_id(final String clientId) {
        this.clientId = clientId;
    }

    public void setRedirect_uri(final String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public void setRefresh_token(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setClient_secret(final String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
