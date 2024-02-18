package com.user.management.sittings.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenConfiguration {

    private String secret;
    private String accessTokenTime;
    private String refreshTokenTime;

}
