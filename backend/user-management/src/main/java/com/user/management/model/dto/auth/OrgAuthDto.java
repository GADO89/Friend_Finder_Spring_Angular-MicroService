package com.user.management.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.management.model.dto.role.RoleDto;
import com.user.management.model.enums.Scope;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgAuthDto {

    @JsonProperty("login_name")
    private Long orgId;

    @JsonProperty("access_Token")
    private String access_token;

    @JsonProperty("expire_at")

    private Date expireAt;
    @JsonProperty("refresh_token")

    private String refreshToken;

    @JsonProperty("roles")
    private List<RoleDto> roles;

    @JsonProperty("scope")
    private Scope scope;

}
