package com.prime.app.module.company;

import com.prime.app.config.GlobalSetting;
import com.prime.app.config.exceptions.ApplicationException;
import com.prime.app.config.exceptions.NoRecordFoundException;
import com.prime.app.module.auth.service.user.UserService;
import com.prime.app.module.common.dto.PaginationRequest;
import com.prime.app.module.common.enums.IdType;
import com.prime.app.module.common.enums.TenantType;
import com.prime.app.module.common.service.IdSettingService;
import com.prime.app.module.company.dto.SignUpDto;
import com.prime.app.utils.LanguageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository repository;
    private final IdSettingService idSettingService;

//    private final NotificationService notificationService;

    private final UserService userService;

    public Page<String> findAll(String tenantId, PaginationRequest pageable) {
        return repository.findAllData(tenantId, pageable.getSearch(), pageable.getPageable());
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(CompanyDto dto, String tenantId) {
        Company newEntity = new Company();
        newEntity.copyData(dto);
        newEntity.setCode(idSettingService.generateId(IdType.COMPANY, tenantId));
        repository.save(newEntity);
    }


    @Transactional(rollbackFor = Exception.class)
    public void update(CompanyDto dto) throws NoRecordFoundException {
        Company dbObject = repository.findById(dto.getId()).orElseThrow(NoRecordFoundException::new);
        dbObject.copyData(dto);
        repository.save(dbObject);
    }

    public Object getById(String id) throws NoRecordFoundException {
        return repository.getDtoById(id).orElseThrow(NoRecordFoundException::new);
    }

    public String getExportData(String tenantId) throws NoRecordFoundException {
        return repository.getExportData(tenantId).orElseThrow(NoRecordFoundException::new);
    }

    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignUpDto dto) {

        Company newEntity = repository.findByMobile(dto.getMobile());
        if (newEntity == null) {
            newEntity = new Company();
            newEntity.setCode(idSettingService.generateId(IdType.COMPANY, repository.findIdByCode(GlobalSetting.SUPER_COMPANY_CODE)));
            newEntity.setMobile(dto.getMobile());
            newEntity.setName(dto.getName());
            newEntity = repository.save(newEntity);
        } else {
            newEntity.setName(dto.getName());
        }
//        if (!newEntity.isOtpValidated()) {
//            newEntity.setOtp(StringUtils.generateOtp(6));
//            notificationService.sendOtpSms(dto.getMobile(), newEntity.getOtp(), newEntity.getId());
//        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void submitOtp(SignUpDto dto) throws ApplicationException {

        Company newEntity = repository.findByMobile(dto.getMobile());
//        if (!dto.getOtp().equalsIgnoreCase(newEntity.getOtp())) {
//            throw new ApplicationException("Invalid OTP");
//        }
//        newEntity.setOtpValidated(true);
        newEntity = repository.save(newEntity);
        idSettingService.createDefaultIdSetting(newEntity.getId());
        userService.createDefaultUserData(newEntity.getId(), newEntity.getMobile(), null, newEntity.getCode(), TenantType.ADMIN, LanguageUtils.toMultiLang(newEntity.getName()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateProfile(CompanyDto dto, String tenantId) throws NoRecordFoundException {

        Company dbObject = repository.findById(tenantId).orElseThrow(NoRecordFoundException::new);
        dbObject.copyData(dto);
        repository.save(dbObject);

    }
}
