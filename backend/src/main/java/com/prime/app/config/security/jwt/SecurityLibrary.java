package com.prime.app.config.security.jwt;

import com.prime.app.utils.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Locale;

@Log4j2
public class SecurityLibrary {

    private SecurityLibrary() {

    }

    public static String getTenantId() {
        return getAuthenticatedUser().getTenantId();
    }

    public static String getId() {
        return getAuthenticatedUser().getId();
    }

    private static AuthenticatedUser getAuthenticatedUser() {
        if (SecurityContextHolder.getContext() == null) {
            throw new SecurityException();
        }

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if (currentUser == null) {
            throw new SecurityException();
        }

        return (AuthenticatedUser) currentUser.getPrincipal();
    }

    public static String getLangCode() {
        try {
            String lang = getAuthenticatedUser().getLangCode();
            return StringUtils.isNotEmpty(lang) ? lang : Locale.ENGLISH.getLanguage();
        } catch (Exception e) {
            return Locale.ENGLISH.getLanguage();
        }
    }

    /**
     * Do not Use this method anywhere else
     *
     * @return user
     */
    public static AuthenticatedUser getForAuditAware() {
        try {
            Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
            if (currentUser.getPrincipal() != null) {
                return ((AuthenticatedUser) currentUser.getPrincipal());
            }
        } catch (Exception e) {
            log.debug("error for audit aware:{}", e.getMessage(), e);
        }
        return null;
    }

    public static int getDecimalScale() {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            LoginUserDetails u = ((LoginUserDetails) currentUser.getPrincipal());
            return u.getDecimalScale();
        } catch (Exception e) {
            return 2;
        }
    }

}
