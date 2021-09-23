package com.henry.home.oauth.starter.model.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserToken {

    private Long id;

    private String nickName;

    private String mobile;

    private String email;

    private Long avatarId;
}
