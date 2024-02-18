package com.user.management.config.jwt;

import io.jsonwebtoken.JwtBuilder;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.management.model.user.User;
import com.user.management.sittings.Configurations;

@Component
public class AccessTokenUserHandler extends TokenHandler<User> {

    private Duration accessTokenTtl;
    private Duration refreshTokenTtl;
    /*
     * constructor to build JwtBuilder && JwtParser
     * @param Configurations
     */
    @Autowired
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
    public String createToken(User user) {
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
