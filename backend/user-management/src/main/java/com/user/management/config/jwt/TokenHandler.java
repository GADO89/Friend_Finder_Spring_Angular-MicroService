package com.user.management.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

import com.user.management.Configurations;

public abstract class TokenHandler<A> {

    protected JwtBuilder builder;

    protected JwtParser parser;

    /*
    * constructor to build JwtBuilder && JwtParser
    * @param Configurations
    */
    public TokenHandler(Configurations configurations) {
        Key key = Keys.hmacShaKeyFor(configurations.getToken().getSecret()
                        .getBytes(StandardCharsets.UTF_8));

        this.builder = Jwts.builder().signWith(key);
        this.parser = Jwts.parserBuilder().setSigningKey(key).build();
    }
    /*
     * create token
     * @param subject
     * @param duration
     *  @return JwtBuilder
     */

    protected JwtBuilder createToken(String subject, Duration duration) {
        Date issueAt = new Date(); //set Date
        Date expiration = Date.from(issueAt.toInstant().plus(duration));
        return builder.setSubject(subject).setIssuedAt(issueAt).setExpiration(expiration);

    }

    /* to validate token
     * @param token
     * @return boolean
     */
    public boolean isValidToken(String token) {
        if (parser.isSigned(token)) {
            Claims claims = parser.parseClaimsJws(token).getBody();

            Date issueAt = claims.getIssuedAt();
            Date expiration = claims.getExpiration();

        }
        return false;
    }
    /* to get subject
     * @param token
     * @return String
     */

    public String getSubject(String token) {
        return parser.parseClaimsJws(token).getBody().getSubject();
    }

    /*
     * create custom token
     * @param Param
     * @return String
     */
    public abstract String createUserToken(A Param);

}
