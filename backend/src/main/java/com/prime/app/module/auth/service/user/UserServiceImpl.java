package com.prime.app.module.auth.service.user;

import com.prime.app.module.auth.dto.AuthUserProjection;
import com.prime.app.module.auth.entity.AccessRights;
import com.prime.app.module.auth.entity.User;
import com.prime.app.module.auth.entity.UserRole;
import com.prime.app.module.auth.repository.UserRepository;
import com.prime.app.module.auth.repository.UserRoleRepository;
import com.prime.app.module.auth.service.acl.AccessRightsService;
import com.prime.app.module.common.dto.MultiLangText;
import com.prime.app.module.common.enums.Status;
import com.prime.app.module.common.enums.TenantType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserSecurityService {

    private final UserRepository repository;

    private final UserRoleRepository userRoleRepository;

    private final AccessRightsService accessRightsService;

    private final PasswordEncoder passwordEncoder;

//    private final IdSettingService idSettingService;

    @Override
    public Optional<AuthUserProjection> getAuthUserByLoginId(String username) {
        return repository.getAuthUserByLoginId(username);
    }

    @Async("asyncExecutor")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFailedAttemptes(String id, int failed) {
        repository.updateFailedAttemptes(id, failed);

    }

    @Async("asyncExecutor")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void lockUser(String id) {
        repository.lockUser(id);
    }

    @Async("asyncExecutor")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserLastLoginDetails(Date date, String clientIp, String id) {
        repository.updateUserLastLoginDetails(date, clientIp, id);
    }

//    @Override
//    public List<String> getAcls(String loggedInUser) {
//        return repository.getAllAcls(loggedInUser);
//    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void updateTimeZone(String timeZone, String id) {
//        repository.updateTimeZone(timeZone, id);
//    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void updateLangCode(String lanCode, String id) {
//        repository.updateLangCode(lanCode, id);
//    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void updateOtp(String otp, String id, int otpSentCount) {
//        repository.updateOtp(otp, new Date(), id, otpSentCount);
//    }

//    @Override
//    public boolean checkPermission(String authUser, String[] roles) {
//        return repository.checkPermission(roles[0], authUser);
//    }

    @Override
    public Object getLoginUserData(String id) {
        return repository.getLoginUserData(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User createDefaultUserData(String tenantId, String loginId, String password, String code, TenantType tenantType, MultiLangText name) {
        User user = new User();
        user.setLoginId(loginId);
        user.setPassword(passwordEncoder.encode("Super1234"));
        user.setCode(code);
        user.setName(name);
        setDefaultUserData(tenantId, user);
        user.setTenantType(tenantType);
        user = repository.save(user);
        createOwnerDefaultRole(user);
        return user;
    }

//    @Override
//    public Object getById(String id) throws NoRecordFoundException {
//        return repository.getDtoById(id).orElseThrow(NoRecordFoundException::new);
//    }

//    @Transactional(rollbackFor = Exception.class)
//    public void save(UserDto dto, String tenantId) throws ApplicationException {
//        User newEntity = new User();
////        newEntity.setCode(idSettingService.generateId(IdType.USER, tenantId));
//        newEntity.copyData(dto);
//        setDefaultUserData(tenantId, newEntity);
//        newEntity.setTenantType(TenantType.SUPER_ADMIN);
//        repository.save(newEntity);
//    }

    private static void setDefaultUserData(String tenantId, User newEntity) {
        newEntity.setTenantId(tenantId);
        newEntity.setDeleted(false);
        newEntity.setIsAdmin(true);
        newEntity.setLocked(false);
        newEntity.setStatus(Status.ACTIVE);
    }

//    @Transactional(rollbackFor = Exception.class)
//    public void update(UserDto dto) throws NoRecordFoundException, ApplicationException {
//        User dbObject = repository.findById(dto.getId()).orElseThrow(NoRecordFoundException::new);
//        dbObject.copyData(dto);
//        repository.save(dbObject);
//    }

//    public Page<String> findAll(String tenantId, PaginationRequest pageable) {
//        return repository.findAllData(tenantId, pageable.getSearch(), pageable.getPageable());
//    }

    private void createOwnerDefaultRole(User user) {
        UserRole ownerRole = userRoleRepository.findByCodeAndTenantId(user.getTenantType().name(), user.getTenantId());
        if (ownerRole == null) {
            log.info("Creating role.......super admin");
            UserRole userRole = new UserRole();
            userRole.setCode(TenantType.SUPER_ADMIN.name());
            userRole.setName(new MultiLangText(TenantType.SUPER_ADMIN.name()));
            userRole.setDescription(new MultiLangText("System Administrator"));
            List<AccessRights> aclList = accessRightsService.getAllAccessControlList(user.getTenantType());
            for (AccessRights rights : aclList) {
                if (!aclList.contains(rights)) {
                    aclList.add(rights);
                }
            }
            userRole.setStatus(Status.ACTIVE);
            userRole.setAccessControlList(aclList);
            userRole.setTenantId(user.getTenantId());
            userRole = userRoleRepository.save(userRole);
            user.setUserRole(userRole);
            repository.save(user);
        }
    }

//    public List<Object> getDropdownData(String search, String tenantId) {
//        return repository.getDropdownData(tenantId);
//    }

//    @Override
//    public String getExportData(String tenantId) throws NoRecordFoundException {
//        return repository.getExportData(tenantId).orElseThrow(NoRecordFoundException::new);
//    }

//    @Override
//    public Optional<OtpUserProjection> getOtpUserByLoginId(String mobile) {
//        return repository.getOtpUserByLoginId(mobile);
//    }

//    @Override
//    public Optional<AuthUserProjection> getAuthUserById(String id) {
//        return repository.getAuthUserById(id);
//    }


}
