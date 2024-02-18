package com.user.management.config.jwt;

import io.jsonwebtoken.JwtBuilder;

import java.time.Duration;

import org.springframework.stereotype.Component;

import com.user.management.Configurations;
import com.user.management.model.organization.Organization;

@Component
public class AccessTokenOrganizationHandler extends TokenHandler<Organization> {

    private Duration accessTokenTtl;
    private Duration refreshTokenTtl;
    /*
     * constructor to build JwtBuilder && JwtParser
     * @param Configurations
     */
    public AccessTokenOrganizationHandler(Configurations configurations) {
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
    public String createUserToken(Organization organization) {
        JwtBuilder tokenBuilder =
                        createToken(organization.getId().toString(), accessTokenTtl);
        tokenBuilder.claim("referencerId", organization.getReferencerId());
        tokenBuilder.claim("scope", organization.getScope());

        return tokenBuilder.compact();
    }

    /*
     * refresh custom token
     * @param user
     * @return String
     */
    public String refreshUserToken(Organization organization) {
        return createToken(organization.getId().toString(), accessTokenTtl).compact();
    }

}
