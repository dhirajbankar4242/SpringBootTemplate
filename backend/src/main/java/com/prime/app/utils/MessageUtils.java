package com.prime.app.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MessageUtils {

    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    public static String get(String key, String... values) {
        try {
            return messageSource.getMessage(key, values, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            log.error("error while get message:{}", e.getMessage(), e);
        }
        return key;
    }

    public static String get(String key) {
        try {
            if (key == null) return null;
            return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            log.error("error while get message:{}", e.getMessage(), e);
        }
        return key;
    }

}
