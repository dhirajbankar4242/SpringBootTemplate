package com.prime.app.config.language;

import com.prime.app.config.security.jwt.SecurityLibrary;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

public class LanguageResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        return Locale.forLanguageTag(SecurityLibrary.getLangCode());
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        // nothing
    }
}
