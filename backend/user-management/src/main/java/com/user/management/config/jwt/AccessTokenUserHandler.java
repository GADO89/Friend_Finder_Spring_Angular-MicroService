package com.user.management.config.jwt;

import io.jsonwebtoken.JwtBuilder;

import java.time.Duration;

import org.springframework.stereotype.Component;

import com.user.management.Configurations;
import com.user.management.model.user.User;

@Component
public class AccessTokenUserHandler extends TokenHandler<User> {

    private Duration accessTokenTtl;
    private Duration refreshTokenTtl;
    /*
     * constructor to build JwtBuilder && JwtParser
     * @param Configurations
     */
    public AccessTokenUserHandler(Configurations configurations) {
        super(configurations);
        this.accessTokenTtl = configurations.getToken().getAccessTokenTime();
        this.refreshTokenTtl = configurations.getToken().getRefreshTokenTime();

    }
    /*
     * create custom token
     * @param Param
     * @return String
     */

    @Override
    public String createUserToken(User user) {
        JwtBuilder tokenBuilder = createToken(user.getId().toString(), accessTokenTtl);
        tokenBuilder.claim("loginName", user.getName());
        tokenBuilder.claim("scope", user.getScope());

        return tokenBuilder.compact();
    }

    /*
     * refresh custom token
     * @param user
     * @return String
     */
    public String refreshUserToken(User user) {
        return createToken(user.getId().toString(), accessTokenTtl).compact();
    }

}
