package com.prime.app.module.auth.entity;

import com.prime.app.config.security.jwt.LoginUserDetails;
import com.prime.app.module.auth.dto.UserDto;
import com.prime.app.module.common.dto.MultiLangText;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "app_user", indexes = {@Index(columnList = "loginId")})
public class User extends LoginUserDetails {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @GeneratedValue
    private String id;

    @Version
    @Column(name = "optlock", nullable = false)
    private long version = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_user_role_id"))
    private UserRole userRole;

    @JdbcTypeCode(SqlTypes.JSON)
    protected MultiLangText name;

    private Boolean isAdmin = false;

    @Column(length = 128)
    protected String deviceId;

    @Column(length = 64)
    protected String loginId;

    @Column
    private String password;

//    @Column(length = 128)
//    private String otp; //encrypted

    @Column(length = 1)
    protected Integer failedAttempts;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastLoginTime;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastFailedLoginTime;

    @Column(length = 64)
    protected String loginIpAddress;

//    @Temporal(TemporalType.TIMESTAMP)
//    protected LocalDateTime otpDate;

    @Column(length = 32)
    protected String latitude;

    @Column(length = 32)
    protected String longitude;

    @Column(length = 64)
    protected String email;

    @Column(length = 16)
    protected String mobile;

    @Column(length = 16)
    protected String whatsAppNumber;

//    private Integer otpSentCount = 0;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_created_by"), updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    protected User createdBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_modified_by"))
    @LastModifiedBy
    protected User modifiedBy;

    public User(String id) {
        this.id = id;
    }

    public void copyData(UserDto dto) {
        this.userRole = new UserRole(dto.getUserRole());
        this.name = dto.getName();
        this.loginId = dto.getLoginId();
        this.email = dto.getEmail();
        this.mobile = dto.getMobile();
        this.whatsAppNumber = dto.getWhatsAppNumber();
    }
}
