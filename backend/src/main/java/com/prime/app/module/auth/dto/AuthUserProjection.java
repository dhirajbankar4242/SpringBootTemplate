package com.prime.app.module.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prime.app.module.common.enums.Status;
import com.prime.app.module.common.enums.TenantType;

public interface AuthUserProjection {

    @JsonIgnore
    String getId();

    String getLoginId();

    String getCode();

    String getPassword();

    @JsonIgnore
    String getDeviceId();

    String getTimeZone();


    Integer getDecimalScale();

    @JsonIgnore
    Boolean getLocked();

    @JsonIgnore
    Status getStatus();

    TenantType getTenantType();

    @JsonIgnore
    String getTenantId();

    String getLangCode();

    @JsonIgnore
    Integer getFailedAttempts();

}
