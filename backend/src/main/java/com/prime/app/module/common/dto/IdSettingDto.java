package com.prime.app.module.common.dto;

import com.prime.app.module.common.enums.IdSettingPattern;
import com.prime.app.module.common.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class IdSettingDto {

    private String id;

    private String name;

    private String description;

    private Status status;

    private Integer idSequence;

    private String idPerfix;

    private String idDelimiter;

    private String idDatePattern;

    private Integer paddingLength;

    private IdSettingPattern idSettingPattern;

    private Boolean isNumeric;

    private Boolean category;

    private Boolean subCategory;

}
