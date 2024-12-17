package com.prime.app.module.auth.entity;

import com.prime.app.module.auth.dto.UserRoleDto;
import com.prime.app.module.common.dto.MultiLangText;
import com.prime.app.module.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "app_user_role")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserRole extends BaseEntity {

    @JdbcTypeCode(SqlTypes.JSON)
    private MultiLangText name;

    @Column(updatable = false,length = 64)
    private String code;

    @JdbcTypeCode(SqlTypes.JSON)
    private MultiLangText description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_role_acl_mapping", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "acl"), indexes = {@Index(columnList = "acl"), @Index(columnList = "role_id")})
    private List<AccessRights> accessControlList;

    public UserRole(String id) {
        this.setId(id);
    }

    public void copyData(UserRoleDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.status = dto.getStatus();
    }


}
