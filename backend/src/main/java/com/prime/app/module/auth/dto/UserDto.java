package com.prime.app.module.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prime.app.module.common.dto.MultiLangText;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String id;

    @JsonProperty(value = "user_role")
    private String userRole;

    protected MultiLangText name;

    @JsonProperty(value = "login_id")
    protected String loginId;

    protected String password;

    protected String email;

    protected String mobile;

    @JsonProperty(value = "whats_app_number")
    protected String whatsAppNumber;
}
