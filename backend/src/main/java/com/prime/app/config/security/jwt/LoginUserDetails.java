package com.prime.app.config.security.jwt;


import com.prime.app.module.common.enums.Status;
import com.prime.app.module.common.enums.TenantType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class LoginUserDetails {

    @Column(updatable = false, length = 64)
    protected String code;

    @Column(length = 8)
    protected String timeZone;

    @Column(length = 3)
    protected String langCode;

    @Column(length = 1)
    protected Integer decimalScale;

    protected Boolean locked = false;

    protected Boolean deleted = false;

    private Boolean isAdmin = false;

    @Column(length = 16)
    @Enumerated(EnumType.STRING)
    protected Status status;

    @Column(length = 16)
    @Enumerated(EnumType.STRING)
    protected TenantType tenantType;

    @Column(length = 64)
    protected String tenantId;


}
