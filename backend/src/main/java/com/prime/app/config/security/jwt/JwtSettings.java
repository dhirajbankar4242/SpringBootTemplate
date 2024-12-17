package com.prime.app.config.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Set;

public class JwtSettings {

    private JwtSettings() {

    }

    public static final Set<GrantedAuthority> grantedAuthorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";

    public static final Integer TOKEN_EXPIRATION_TIME = 35000; // 30 days

    public static final String TOKEN_ISSUER = "user";

    public static final String TOKEN_SIGNING_KEY = "27wZpG-85bWjx-YM3Yn9-UlGtli-itqaf7-qPR0S2-aqzO5M-zajMDB-giLcMV-l2ieJs";

    public static final String REFRESH_TOKEN_SIGNING_KEY = "28wZpG-85bWjx-YM3Yn9-UlGtli-itqaf7-qPR0S2-aqzO5M-zajMDB-giLcMV-l2ieQs";

    public static final Integer REFRESH_TOKENEXP_TIME = 30; // days


    public static final String HEADER_PREFIX = "Bearer";
    public static final String INVALID_AUTH = "Invalid authorization";
    public static final int HEADER_LENGTH = HEADER_PREFIX.length() + 1;

    public static final String TOKEN_ID = "id";
    public static final String KEY_TENANT_TYPE = "tenantType";

    public static final String TIMEZONE = "timezone";
    public static final String LANG_CODE = "langCode";
    public static final String DECIMAL_SCALE = "decimalScale";
    public static final String TENANT_ID = "tenantId";

}
