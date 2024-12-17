package com.prime.app.module.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "app_access_rights", indexes = {//
        @Index(columnList = "parent_acl_value"), //
        @Index(columnList = "owner"),//
        @Index(columnList = "client"),//
        @Index(columnList = "vendor"),})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AccessRights {

    @Id
    private String aclValue;

    @Version
    @Column(name = "optlock", nullable = false)
    private long version = 0L;

    @Column(length = 512)
    private String aclName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_parent_access"))
    private AccessRights parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @OrderBy("acl_order")
    private List<AccessRights> children;

    @Column(length = 512)
    private String menuLink;

    @Column(length = 2)
    private Integer aclOrder;

    @Column(length = 1)
    private Boolean owner;

    @Column(length = 1)
    private Boolean client;

    @Column(length = 1)
    private Boolean vendor;

    @Transient
    private String parentAclName;

    @Transient
    private boolean checked;

    public AccessRights(String acl) {
        this.aclValue = acl;
    }

}
