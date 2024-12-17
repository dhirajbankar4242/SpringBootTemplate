package com.prime.app.module.auth.service.userrole;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRoleServiceImpl implements UserRoleService {

//    private final UserRoleRepository userRoleRepository;
//
//    private final IdSettingService idSettingService;
//
//    @Override
//    public UserRole findById(String id) throws NoRecordFoundException {
//        return userRoleRepository.findById(id).orElseThrow(NoRecordFoundException::new);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public UserRole save(UserRole dataObject) {
//        return userRoleRepository.save(dataObject);
//    }


//    @Override
//    public boolean existsByNameAndTenantId(String name, String tenantId) {
//        return userRoleRepository.existsByNameAndTenantId(name.toUpperCase(), tenantId);
//    }

//    @Override
//    public List<AccessRights> getAcls(String userRole) {
//        return userRoleRepository.getAcls(userRole);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void update(UserRole dbObject) {
//        userRoleRepository.save(dbObject);
//    }
//
//    @Override
//    public Object getById(String id) throws NoRecordFoundException {
//        return userRoleRepository.getDtoById(id).orElseThrow(NoRecordFoundException::new);
//    }
//
//    @Override
//    public Page<String> findAll(String tenantId, PaginationRequest pageable) {
//        return userRoleRepository.findAllData(tenantId, pageable.getSearch(), pageable.getPageable());
//    }
//
//    @Transactional
//    public void save(UserRoleDto dto, String tenantId) throws ApplicationException {
//
//        UserRole newEntity = new UserRole();
//        newEntity.copyData(dto);
//        newEntity.setTenantId(tenantId);
//        newEntity.getCreatedBy();
//        newEntity.getCreatedDate();
//        newEntity.getModifiedBy();
//        newEntity.getModifiedDate();
//        newEntity.setCode(idSettingService.generateId(IdType.USER_ROLE,tenantId));
//        userRoleRepository.save(newEntity);
//
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void update(UserRoleDto dto) throws NoRecordFoundException, ApplicationException {
//        UserRole dbObject = userRoleRepository.findById(dto.getId()).orElseThrow(NoRecordFoundException::new);
//        dbObject.copyData(dto);
//        userRoleRepository.save(dbObject);
//    }
//
//    @Override
//    public String getExportData(String tenantId) throws NoRecordFoundException {
//        return userRoleRepository.getExportData(tenantId).orElseThrow(NoRecordFoundException::new);
//    }
//
//    @Override
//    public List<Object> getDropdownData(String search, String tenantId) {
//        return userRoleRepository.getDropdownData(tenantId);
//    }

}
