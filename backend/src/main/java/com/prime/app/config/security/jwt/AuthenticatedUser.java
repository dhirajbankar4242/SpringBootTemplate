package com.prime.app.config.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prime.app.module.common.enums.TenantType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class AuthenticatedUser extends LoginUserDetails implements UserDetails {


    private String id;

    public AuthenticatedUser() {

    }

    public AuthenticatedUser(String id, String timeZone, String langCode, Integer decimalScale, TenantType tenantType, String tenantId, String code) {
        this.setId(id);
        this.timeZone = timeZone;
        this.langCode = langCode;
        this.decimalScale = decimalScale;
        this.tenantType = tenantType;
        this.tenantId = tenantId;
        this.code = code;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return false;
    }
}
