package com.prime.app.module.auth.dto;

import com.prime.app.module.common.dto.MultiLangText;
import com.prime.app.module.common.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleDto {

    private String id;

    private MultiLangText name;

    private Status status;

}
