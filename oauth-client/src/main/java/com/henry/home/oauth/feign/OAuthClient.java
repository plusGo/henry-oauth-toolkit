package com.henry.home.oauth.feign;

import com.henry.home.oauth.model.request.OAuthRequest;
import com.henry.home.oauth.token.OAuthToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "HenryOAuthAPI", url = "${henry.oauth.client.authServerUri}")
public interface OAuthClient {
    @PostMapping(path = "token")
    OAuthToken token(@RequestBody final OAuthRequest request);
}
