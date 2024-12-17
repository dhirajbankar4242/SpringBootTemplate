package com.prime.app.config.ratelimit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;


@Component
public class RequestInterceptor implements HandlerInterceptor {
    private final RequestRateLimiter rateLimiter;

    @Autowired
    public RequestInterceptor(CacheManager cacheManager) {
        this.rateLimiter = new RequestRateLimiter(cacheManager); // Set maxRequests accordingly
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIdentifier = getClientIdentifier(request);

        if (rateLimiter.allowRequest(clientIdentifier)) {

            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            localeResolver.setLocale(request, response, localeResolver.resolveLocale(request));

            return true; // Allow the request to proceed
        } else {
            response.setStatus(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE);
            return false; // Reject the request due to rate limit exceeded
        }
    }

    private String getClientIdentifier(HttpServletRequest request) {
        return request.getRemoteAddr();
    }


}
