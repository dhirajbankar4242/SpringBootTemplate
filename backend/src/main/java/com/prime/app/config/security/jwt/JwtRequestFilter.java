package com.prime.app.config.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.prime.app.module.common.enums.TenantType;
import com.prime.app.utils.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {

    private final Algorithm algorithm = Algorithm.HMAC512(JwtSettings.TOKEN_SIGNING_KEY);

    private final JWTVerifier verifier = JWT.require(algorithm).withIssuer(JwtSettings.TOKEN_ISSUER).acceptExpiresAt(JwtSettings.TOKEN_EXPIRATION_TIME).build();


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.equals("/company/sign-up") || path.equals("/company/resend-otp") || path.equals("/company/submit-otp");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String header = request.getHeader(JwtSettings.JWT_TOKEN_HEADER_PARAM);

        if (SecurityContextHolder.getContext().getAuthentication() == null && header != null) {

            AuthenticatedUser userContext = get(verifier.verify(extract(header)));

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userContext, null, null));

        }

        chain.doFilter(request, response);
    }

    public AuthenticatedUser get(DecodedJWT decodedJWT) {

        AuthenticatedUser user = new AuthenticatedUser();

        user.setId(decodedJWT.getClaim(JwtSettings.TOKEN_ID).asString());
        user.setTimeZone(decodedJWT.getClaim(JwtSettings.TIMEZONE).asString());
        user.setLangCode(decodedJWT.getClaim(JwtSettings.LANG_CODE).asString());
        user.setDecimalScale(decodedJWT.getClaim(JwtSettings.DECIMAL_SCALE).asInt());
        user.setTenantId(decodedJWT.getClaim(JwtSettings.TENANT_ID).asString());
        user.setTenantType(TenantType.valueOf(decodedJWT.getClaim(JwtSettings.KEY_TENANT_TYPE).asString()));

        return user;
    }

    public String extract(String header) {
        if (StringUtils.isEmpty(header) || header.length() < JwtSettings.HEADER_LENGTH) {
            throw new AuthenticationServiceException(JwtSettings.INVALID_AUTH);
        }
        return header.substring(JwtSettings.HEADER_LENGTH);
    }


}
