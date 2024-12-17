package com.prime.app.module.common.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MultiLangText {

    public MultiLangText(String text) {
        this.en = text;
        this.mr = text;
    }

    @Size(max = 512, message = "{max.size}") String en;

    @Size(max = 512, message = "{max.size}") String mr;
}
