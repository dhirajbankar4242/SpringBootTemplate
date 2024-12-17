package com.prime.app.module.company;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Company {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @GeneratedValue
    protected String id;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @Version
    @Column(name = "optlock", nullable = false)
    protected long version = 0L;

    @Column(length = 128)
    private String name;

    @Column(length = 14)
    private String mobile;

//    @Column(length = 14)
//    private String password;

//    @Column(length = 6)
//    private String otp;

    @Column(updatable = false,length = 64)
    private String code;

    @Column
    private String address;

    @Column(length = 15)
    private String gstNumber;

    @Column(length = 128)
    private String personName;

    @Column(length = 128)
    private String email;

    @Column(length = 128)
    private String registrationNumber;

    @Column(length = 14)
    private String personMobile;

//    private boolean otpValidated = false;

    public void copyData(CompanyDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.code = dto.getCode();
        this.mobile = dto.getMobile();
//        this.password = dto.getPassword();
        this.address = dto.getAddress();
        this.gstNumber = dto.getGstNumber();
        this.registrationNumber = dto.getRegistrationNumber();
        this.personName = dto.getPersonName();
        this.personMobile = dto.getPersonMobile();
        this.email = dto.getEmail();
    }
}
