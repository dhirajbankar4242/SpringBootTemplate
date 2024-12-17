package com.prime.app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prime.app.module.common.dto.MultiLangText;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LanguageUtils {

    private LanguageUtils() {
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static MultiLangText toMultiLang(String text) {
        try {
            if (text != null) {
                MultiLangText txt = objectMapper.readValue(text, MultiLangText.class);
                if (txt != null) {
                    if (StringUtils.isEmpty(txt.getMr())) {
                        txt.setMr(txt.getEn());
                    } else if (StringUtils.isEmpty(txt.getEn())) {
                        txt.setEn(txt.getMr());
                    }
                    return txt;
                }
            }
        } catch (Exception e) {
            log.error(text + ":" + e.getMessage());
        }
        return new MultiLangText(text);
    }

    public static String toMultiLang(String enText, String mrText) {
        MultiLangText txt = new MultiLangText();
        txt.setEn(enText);
        txt.setMr(mrText);
        return fromMultiLang(txt);
    }

    public static String fromMultiLang(MultiLangText text) {
        try {
            if (text != null) {
                return objectMapper.writeValueAsString(text);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String getMultiLangText(String text) {
        try {
            if (text != null) {
                return objectMapper.writeValueAsString(new MultiLangText(text));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
