package com.prime.app.module.auth.service.user;


import com.prime.app.module.auth.dto.AuthUserProjection;
import com.prime.app.module.auth.entity.User;
import com.prime.app.module.common.dto.MultiLangText;
import com.prime.app.module.common.enums.TenantType;

import java.util.Date;
import java.util.Optional;

public interface UserService {

    Optional<AuthUserProjection> getAuthUserByLoginId(String username);

    void updateFailedAttemptes(String id, int failed);

    void lockUser(String id);

      void updateUserLastLoginDetails(Date date, String clientIp, String id);
//
//    List<String> getAcls(String loggedInUser);
//
//    void updateTimeZone(String timeZone, String id);

//    void updateLangCode(String lanCode, String id);
//
//    void updateOtp(String newPassword, String id, int i);

    Object getLoginUserData(String id);

    User createDefaultUserData(String tenantId, String loginId, String password, String code, TenantType tenantType, MultiLangText name);

//    Object getById(String id) throws NoRecordFoundException;

//    Page<String> findAll(String tenantId, PaginationRequest pageable);

//    void save(UserDto dto, String tenantId) throws ApplicationException;

//    void update(UserDto dto) throws NoRecordFoundException, ApplicationException;

//    public List<Object> getDropdownData(String search, String tenantId);
//
//    String getExportData(String tenantId) throws NoRecordFoundException;
//
//    Optional<OtpUserProjection> getOtpUserByLoginId(String mobile);

//    Optional<AuthUserProjection> getAuthUserById(String id);
}
