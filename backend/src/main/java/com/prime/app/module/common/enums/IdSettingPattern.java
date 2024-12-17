package com.prime.app.module.common.enums;

public enum IdSettingPattern {

    STANDARD("STANDARD (PRE/2018/00001)"), PRE_DATE_DEL_NNNNN("PREDDMMYYYY/NNNNN (PRE2018/00001)"),
    PRE_DEL_DATE_DEL_NNNN("PRE/DDMMYYYY/NNNN (PRE/2018/00001)"), PRE_DEL_DATE_NNNN("PRE/DDMMYYYYNNNN (PRE/2018001)");

    private String value;

    IdSettingPattern(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static IdSettingPattern getIdSettingType(String value) {
        for (IdSettingPattern idSettingPattern : IdSettingPattern.values()) {
            if (value.equals(idSettingPattern.getValue())) {
                return idSettingPattern;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

}
