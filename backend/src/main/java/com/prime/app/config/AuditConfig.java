package com.prime.app.config;

import com.prime.app.config.security.jwt.AuthenticatedUser;
import com.prime.app.config.security.jwt.SecurityLibrary;
import com.prime.app.module.auth.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(modifyOnCreate = false)
public class AuditConfig {

    @Bean
    public AuditorAware<User> createAuditorProvider() {
        return new SecurityAuditor();
    }

    public static class SecurityAuditor implements AuditorAware<User> {
        @Override
        public Optional<User> getCurrentAuditor() {

            AuthenticatedUser details = SecurityLibrary.getForAuditAware();
            User u = null;
            if (details != null) {
                u = new User(details.getId());
            }
            return Optional.ofNullable(u);
        }
    }
}
