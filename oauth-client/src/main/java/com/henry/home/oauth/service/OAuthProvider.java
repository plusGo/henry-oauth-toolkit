package com.henry.home.oauth.service;

import com.henry.home.oauth.configuration.OAuthProperties;
import com.henry.home.oauth.enums.GrantType;
import com.henry.home.oauth.feign.OAuthClient;
import com.henry.home.oauth.model.model.UserToken;
import com.henry.home.oauth.model.request.OAuthRequest;
import com.henry.home.oauth.token.OAuthToken;
import com.henry.home.oauth.util.RSAUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.interfaces.RSAPublicKey;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class OAuthProvider {
    private final OAuthClient oAuthClient;
    private final OAuthProperties oAuthProperties;

    public OAuthToken getTokenByAuthorizationCode(final String code) {
        final OAuthRequest request = OAuthRequest.builder()
                .clientId(oAuthProperties.getClientId())
                .redirectUri(oAuthProperties.getRedirectUri())
                .grantType(GrantType.AUTHORIZATION_CODE.name().toUpperCase())
                .code(code)
                .build();
        return oAuthClient.token(request);
    }

    public UserToken getUserToken(final OAuthToken token, final String publicKeyStr) {
        final RSAPublicKey publicKey = RSAUtil.deSerializePublicKey(publicKeyStr);
        final Jws<Claims> claimsJws = Jwts.parser().setSigningKey(publicKey)
                .parseClaimsJws(token.getIdToken());

        final Long avatarId = Optional.ofNullable(claimsJws.getBody().get("avatarId"))
                .map(val -> Long.parseLong((String) val))
                .orElse(null);

        return UserToken.builder()
                .id(Long.valueOf(claimsJws.getBody().getSubject()))
                .nickName((String) claimsJws.getBody().get("nickName"))
                .mobile((String) claimsJws.getBody().get("mobile"))
                .avatarId(avatarId)
                .email((String) claimsJws.getBody().get("email"))
                .build();
    }

}
