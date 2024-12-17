package com.prime.app.module.common.service;

import com.prime.app.module.common.entity.IdSetting;
import com.prime.app.module.common.enums.IdSettingPattern;
import com.prime.app.module.common.enums.IdType;
import com.prime.app.module.common.enums.Status;
import com.prime.app.module.common.repository.IdSettingRepository;
import com.prime.app.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IdSettingService {

    private final IdSettingRepository repository;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public String generateId(IdType idType, String tenantId) {

        IdSetting settings = repository.findByTenantIdAndName(tenantId, idType.name());

        String id;
        if (Boolean.TRUE.equals(settings.getIsNumeric())) {
            id = "" + settings.getIdSequence();
        } else {
            id = getIdSettingOnPattern(settings);
        }
        settings.setIdSequence(settings.getIdSequence() + 1);
        repository.save(settings);

        return id;
    }

    private String getIdSettingOnPattern(IdSetting settings) {
        String datepatern = "";
        String squence = "";
        String prefix;
        String del;

        if (StringUtils.checkString(settings.getIdDatePattern()).length() > 0) {
            SimpleDateFormat df = new SimpleDateFormat(settings.getIdDatePattern());
            datepatern = df.format(new Date());
        }

        if (settings.getIdSequence() == null) {
            settings.setIdSequence(1);
        }
        if (settings.getPaddingLength() != null && settings.getPaddingLength() > 0) {
            squence = StringUtils.lpad("" + settings.getIdSequence(), settings.getPaddingLength(), '0');
        } else {
            squence += settings.getIdSequence();
        }
        prefix = settings.getIdPerfix();
        del = settings.getIdDelimiter();

        if (settings.getIdSettingPattern() != null) {
            return switch (settings.getIdSettingPattern()) {
                case PRE_DATE_DEL_NNNNN -> getIdByPatternOrder(prefix, datepatern, del, squence);
                case PRE_DEL_DATE_DEL_NNNN ->
                        getIdByPatternOrder(prefix, del, StringUtils.isEmpty(datepatern) ? "" : (datepatern + del), squence);
                case PRE_DEL_DATE_NNNN -> getIdByPatternOrder(prefix, del, datepatern, squence);
                default -> getIdByPatternOrder(prefix, del, datepatern, del, squence);
            };
        } else {
            if (StringUtils.checkString(datepatern).length() <= 0) del = "";
            return getIdByPatternOrder(prefix, del, datepatern, del, squence);
        }
    }

    private String getIdByPatternOrder(String... args) {
        StringBuilder id = new StringBuilder();
        for (String string : args) {
            id.append(StringUtils.checkString(string));
        }
        return id.toString();
    }

    @Transactional(rollbackFor = Exception.class)
    public void createDefaultIdSetting(String tenantId) {
        for (IdType idType : IdType.values()) {
            IdSetting setting = repository.findByTenantIdAndName(tenantId, idType.name());
            if (setting == null) {
                setting = new IdSetting();
                setting.setIdPerfix(idType.name().substring(0, Math.min(idType.name().length(), 3)));
                setting.setName(idType.name());
                setting.setIsNumeric(true);
                setting.setIdSettingPattern(IdSettingPattern.PRE_DATE_DEL_NNNNN);
                setting.setIdSequence(1);
                setting.setTenantId(tenantId);
                setting.setCreatedBy(null);
                setting.setStatus(Status.ACTIVE);
                setting.setCreatedDate(new Date());
                repository.save(setting);
            }
        }
    }
}
