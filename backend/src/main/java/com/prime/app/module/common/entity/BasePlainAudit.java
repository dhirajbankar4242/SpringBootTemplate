package com.prime.app.module.common.entity;

import com.prime.app.module.auth.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Log4j2
@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BasePlainAudit {


    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @GeneratedValue
    protected String id;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_created_by"), updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    protected User createdBy;

    @Column(length = 64, updatable = false)
    protected String tenantId;

    @Version
    @Column(name = "optlock", nullable = false)
    protected long version = 0L;

}
