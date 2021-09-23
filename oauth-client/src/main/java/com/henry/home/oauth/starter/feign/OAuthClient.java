package com.henry.home.oauth.starter.feign;

import com.henry.home.oauth.starter.model.request.OAuthRequest;
import com.henry.home.oauth.starter.token.OAuthToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

@FeignClient(name = "OAuthAPI", url = "")
public interface OAuthClient {
    @PostMapping(path = "token")
    OAuthToken token(final URI uri, @RequestBody final OAuthRequest request);
}
