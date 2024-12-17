package com.prime.app.module.common.entity;

import com.prime.app.module.common.dto.IdSettingDto;
import com.prime.app.module.common.enums.IdSettingPattern;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(indexes = {@Index(columnList = "tenantId"), @Index(columnList = "name")})
@EntityListeners(AuditingEntityListener.class)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IdSetting extends BaseEntity {

    private String name;

    private Integer idSequence;

    private String idPerfix;

    private String idDelimiter;

    private String idDatePattern;

    private Integer paddingLength;

    @Enumerated(EnumType.STRING)
    private IdSettingPattern idSettingPattern;

    @Column(length = 1024)
    private String description;

    private Boolean isNumeric;

    private Boolean category;

    private Boolean subCategory;

    public IdSetting(String id) {
        this.setId(id);

    }

    public void copyData(IdSettingDto dataObject) {
        this.setDescription(dataObject.getDescription());
        this.setIdDatePattern(dataObject.getIdDatePattern());
        this.setIdDelimiter(dataObject.getIdDelimiter());
        this.setIdPerfix(dataObject.getIdPerfix());
        this.setPaddingLength(dataObject.getPaddingLength());
        this.setIdSettingPattern(dataObject.getIdSettingPattern());
        this.setIsNumeric(dataObject.getIsNumeric());
        this.setCategory(dataObject.getCategory());
        this.setSubCategory(dataObject.getSubCategory());
    }
}
