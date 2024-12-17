package com.prime.app.module.common.entity;

import com.prime.app.module.auth.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseAudit extends BasePlainAudit {

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_modified_by"))
    @LastModifiedBy
    protected User modifiedBy;

}
