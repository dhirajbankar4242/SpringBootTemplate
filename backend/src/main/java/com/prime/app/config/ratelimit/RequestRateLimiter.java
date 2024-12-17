package com.prime.app.config.ratelimit;

import lombok.extern.log4j.Log4j2;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RequestRateLimiter {

    private final Cache rateLimitCache;

    public RequestRateLimiter(CacheManager cacheManager) {
        this.rateLimitCache = cacheManager.getCache("rateLimitCache");
    }

    public boolean allowRequest(String key) {

       // Integer requests = rateLimitCache.get(key, Integer.class);


        /*if (requests == null) {
            requests = 1;
        } else if (requests < 5) {
            requests++;
        } else {
            log.info("key blocked {}", key);
            return false;
        }*/

       // rateLimitCache.put(key, requests);
        return true;
    }

}
