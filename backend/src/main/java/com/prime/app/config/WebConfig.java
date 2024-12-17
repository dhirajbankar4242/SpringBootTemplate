package com.prime.app.config;

import com.prime.app.config.ratelimit.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String[] allowedMethods = {"GET", "POST", "PUT", "PATCH", "DELETE"};

    private final RequestInterceptor requestInterceptor;

    @Autowired
    public WebConfig(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**").allowedOrigins("*").allowedMethods(allowedMethods);

        WebMvcConfigurer.super.addCorsMappings(registry);
    }

//    @Bean
//    public LocaleResolver localeResolver() {
//        return new LanguageResolver();
//    }

//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasenames("message");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(requestInterceptor).addPathPatterns("/**");
//    }

}
