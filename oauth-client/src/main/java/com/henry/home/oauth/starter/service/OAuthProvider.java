package com.henry.home.oauth.starter.service;

import com.henry.home.oauth.starter.configuration.OAuthProperties;
import com.henry.home.oauth.starter.enums.GrantType;
import com.henry.home.oauth.starter.exception.OAuthTokenException;
import com.henry.home.oauth.starter.feign.OAuthClient;
import com.henry.home.oauth.starter.model.model.UserToken;
import com.henry.home.oauth.starter.model.request.OAuthRequest;
import com.henry.home.oauth.starter.token.OAuthToken;
import com.henry.home.oauth.starter.util.RSAUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.interfaces.RSAPublicKey;

@Service
@AllArgsConstructor
@Slf4j
public class OAuthProvider {
    private final OAuthClient oAuthClient;
    private final OAuthProperties oAuthProperties;

    public OAuthToken getTokenByAuthorizationCode(final String code) {
        try {
            final OAuthRequest request = OAuthRequest.builder()
                    .clientId(oAuthProperties.getClientId())
                    .redirectUri(oAuthProperties.getRedirectUri())
                    .grantType(GrantType.AUTHORIZATION_CODE.name().toUpperCase())
                    .code(code)
                    .build();
            final URI apiUri = new URI(oAuthProperties.getAuthServerUri());
            return oAuthClient.token(apiUri, request);

        } catch (URISyntaxException e) {
            throw new OAuthTokenException(e.getMessage(), e);
        }
    }

    public UserToken getUserToken(final OAuthToken token, final String publicKeyStr) {
        final RSAPublicKey publicKey = RSAUtil.deSerializePublicKey(publicKeyStr);
        final Jws<Claims> claimsJws = Jwts.parser().setSigningKey(publicKey)
                .parseClaimsJws(token.getIdToken());

        return UserToken.builder()
                .id(Long.valueOf(claimsJws.getBody().getSubject()))
                .nickName((String) claimsJws.getBody().get("nickName"))
                .mobile((String) claimsJws.getBody().get("mobile"))
                .avatarId(Long.valueOf((String) claimsJws.getBody().get("avatarId")))
                .email((String) claimsJws.getBody().get("email"))
                .build();
    }

}
