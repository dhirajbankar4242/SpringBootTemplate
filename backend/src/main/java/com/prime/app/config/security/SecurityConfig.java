package com.prime.app.config.security;

import com.prime.app.config.security.jwt.JwtAuthenticationEntryPoint;
import com.prime.app.config.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PATH_TO_SKIP = {"/auth/**", "/swagger-ui/**", "/swagger-ui.html", "/api-docs/**", }; //"/company/sign-up", "/company/resend-otp", "/company/submit-otp"

    @Autowired
    @Qualifier("jwtUserDetailsService")
    UserDetailsService jwtUserDetailsService;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http//
                .formLogin(AbstractHttpConfigurer::disable)//
                .httpBasic(AbstractHttpConfigurer::disable)//
                .csrf(AbstractHttpConfigurer::disable)//
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)//
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)//
                        .httpStrictTransportSecurity(hsts -> hsts.includeSubDomains(true).preload(true).maxAgeInSeconds(31536000))//
                        .xssProtection(xssProtection -> xssProtection.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))//
                        .referrerPolicy(referrer -> referrer.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.NO_REFERRER))//
                        .cacheControl(HeadersConfigurer.CacheControlConfig::disable)//
                        .contentSecurityPolicy(policy -> policy.policyDirectives("script-src 'self' https://trustedscripts.example.com; object-src https://trustedplugins.example.com; report-uri /csp-report-endpoint/")).addHeaderWriter(new StaticHeadersWriter("Expect-CT", "max-age=86400,enforce"))//
                        .addHeaderWriter(new StaticHeadersWriter("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains"))//
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.DENY))//
                        .addHeaderWriter(new StaticHeadersWriter("Server", "Server"))//
                        .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "default-src 'self'"))//
                        .addHeaderWriter(new StaticHeadersWriter("X-WebKit-CSP", "default-src 'self'"))//
                ).authorizeHttpRequests(authorize -> authorize.requestMatchers(PATH_TO_SKIP).permitAll().anyRequest().authenticated())//
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))//
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//
                .addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class)//
                .build();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.setConnectTimeout(Duration.ofMillis(60000)).build();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }

}
