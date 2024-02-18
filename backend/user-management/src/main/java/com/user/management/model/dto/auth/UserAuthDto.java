package com.user.management.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.management.model.dto.role.RoleDto;
import com.user.management.model.enums.Scope;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAuthDto {

    @JsonProperty("login_name")
    private Long userId;
    @JsonProperty("access_Token")
    private String acces_token;
    @JsonProperty("expire_at")
    private String expireAt;
    @JsonProperty("refresh_token")
    private String refreshToken;
    private List<RoleDto> roles;
    private boolean admin;
    private Scope scope;

}
