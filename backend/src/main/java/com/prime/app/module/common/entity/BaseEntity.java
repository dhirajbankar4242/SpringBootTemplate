package com.prime.app.module.common.entity;

import com.prime.app.module.common.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends BaseAudit {

    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    protected Status status = Status.ACTIVE;

}
