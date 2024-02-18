package com.user.management.config.jwt;

import io.jsonwebtoken.JwtBuilder;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.management.model.organization.Organization;
import com.user.management.sittings.Configurations;

@Component
public class AccessTokenOrganizationHandler extends TokenHandler<Organization> {

    private Duration accessTokenTtl;

    private Duration refreshTokenTtl;

    /**
     * constructor to build JwtBuilder && JwtParser
     *
     * @param configurations
     */
    @Autowired
    public AccessTokenOrganizationHandler(Configurations configurations) {
        super(configurations);
        this.accessTokenTtl = configurations.getToken().getAccessTokenTime();
        this.refreshTokenTtl = configurations.getToken().getRefreshTokenTime();
    }

    @Override
    public String createToken(Organization organization) {
        JwtBuilder tokenBuilder =
                        createToken(organization.getId().toString(), accessTokenTtl);
        tokenBuilder.claim("referenceId", organization.getReferencerId());
        tokenBuilder.claim("scope", organization.getScope());
        return tokenBuilder.compact();
    }

    /**
     * get token Expire At
     * @param token
     * @return Date
     */
    public String getExpireAt(String token) {
        return parser.parseClaimsJws(token).getBody().getExpiration().toString();
    }

    public String createRefreshToken(Organization organization) {
        return createToken(organization.getId().toString(), accessTokenTtl).compact();
    }

}
