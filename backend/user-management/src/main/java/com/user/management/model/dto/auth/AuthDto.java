package com.user.management.model.dto.auth;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class AuthDto {

    @JsonProperty("login_name")
    private Long userId;
    @JsonProperty("access_Token")
    private String acces_token;
    @JsonProperty("expire_at")
    private String expireAt;
    @JsonProperty("refresh_token")
    private String refreshToken;
    private List<String> roles;
    private boolean admin;
    private String scope;

}
