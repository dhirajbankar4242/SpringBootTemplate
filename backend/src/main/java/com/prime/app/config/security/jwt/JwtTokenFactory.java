package com.prime.app.config.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.prime.app.utils.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Log4j2
@Service
public class JwtTokenFactory {

    private static final String JWT_EXCEPTION_MSG = "Cannot create JWT Token without username";

    private final Algorithm algorithm = Algorithm.HMAC512(JwtSettings.TOKEN_SIGNING_KEY);

    private final Algorithm refreshTokenAlgorithm = Algorithm.HMAC512(JwtSettings.REFRESH_TOKEN_SIGNING_KEY);

    private final JWTVerifier refreshTokenVerifier = JWT.require(refreshTokenAlgorithm).withIssuer(JwtSettings.TOKEN_ISSUER).acceptExpiresAt(JwtSettings.REFRESH_TOKENEXP_TIME).build();

    public String createAccessJwtToken(AuthenticatedUser userContext) {
        if (userContext == null || StringUtils.isEmpty(userContext.getCode())) {
            throw new IllegalArgumentException(JWT_EXCEPTION_MSG);
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, JwtSettings.TOKEN_EXPIRATION_TIME);
        return JWT.create()//
                .withIssuedAt(Calendar.getInstance().getTime())//
                .withExpiresAt(cal.getTime())//
                .withSubject(userContext.getCode())//
                .withIssuer(JwtSettings.TOKEN_ISSUER)//
                .withClaim(JwtSettings.TOKEN_ID, userContext.getId())//
                .withClaim(JwtSettings.TIMEZONE, userContext.getTimeZone())//
                .withClaim(JwtSettings.LANG_CODE, userContext.getLangCode())//
                .withClaim(JwtSettings.DECIMAL_SCALE, userContext.getDecimalScale())//
                .withClaim(JwtSettings.TENANT_ID, userContext.getTenantId())//
                .withClaim(JwtSettings.KEY_TENANT_TYPE, userContext.getTenantType().name())//
                .sign(algorithm);
    }

    public String createRefreshToken(AuthenticatedUser userContext) {
        if (userContext == null || StringUtils.isEmpty(userContext.getCode())) {
            throw new IllegalArgumentException(JWT_EXCEPTION_MSG);
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, JwtSettings.REFRESH_TOKENEXP_TIME);

        return JWT.create()//
                .withIssuedAt(Calendar.getInstance().getTime())//
                .withExpiresAt(cal.getTime())//
                .withSubject(userContext.getCode())//
                .withIssuer(JwtSettings.TOKEN_ISSUER)//
                .sign(refreshTokenAlgorithm);
    }

    public String verifyRefreshToken(String token) {
        return refreshTokenVerifier.verify(token).getSubject();
    }


}
