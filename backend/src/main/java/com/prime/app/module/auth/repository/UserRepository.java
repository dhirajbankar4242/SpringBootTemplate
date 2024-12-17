package com.prime.app.module.auth.repository;

import com.prime.app.module.auth.dto.AuthUserProjection;
import com.prime.app.module.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "select id,code, login_id as loginId,password, device_id as deviceId,decimal_scale as decimalScale,time_zone as TimeZone,locked,deleted,status,tenant_type as TenantType, tenant_id as TenantId,lang_code as LangCode,failed_attempts as failedAttempts from app_user where upper(login_id) = upper(:loginId) and deleted = false", nativeQuery = true)
    Optional<AuthUserProjection> getAuthUserByLoginId(String loginId);

//    @Query(value = "select id, login_id as loginId, locked,deleted,status,tenant_type as TenantType, tenant_id as TenantId,failed_attempts as failedAttempts,otp_sent_count as OtpSentCount from app_user where upper(login_id) = upper(:loginId) and deleted = false", nativeQuery = true)
//    Optional<OtpUserProjection> getOtpUserByLoginId(String loginId);

    @Modifying
    @Query(value = "update app_user set failed_attempts =:failed where id=:id", nativeQuery = true)
    void updateFailedAttemptes(String id, int failed);

    @Modifying
    @Query(value = "update app_user set locked =true where id=:id", nativeQuery = true)
    void lockUser(String id);

    @Modifying
    @Query(value = "update app_user set failed_attempts=0, last_login_time =:lastLoginTime,login_ip_address=:loginIpAddress where id=:id", nativeQuery = true)
    void updateUserLastLoginDetails(Date lastLoginTime, String loginIpAddress, String id);

//    @Query(value = "select distinct a.acl from app_role_acl_mapping a  where  a.role_id = (select u.user_role_id from app_user u  where u.id = :id)", nativeQuery = true)
//    List<String> getAllAcls(String id);

//    @Modifying
//    @Query(value = "update  app_user  set lang_code = :langCode where id = :id", nativeQuery = true)
//    void updateLangCode(String langCode, String id);

//    @Modifying
//    @Query(value = "update  app_user  set time_zone = :timeZoneToSet where id = :id", nativeQuery = true)
//    void updateTimeZone(String timeZoneToSet, String id);

//    @Modifying
//    @Query(value = "update  app_user  set otp = :otp, otp_date =:todayDate,otp_sent_count=:otpSentCount  where id = :id ", nativeQuery = true)
//    void updateOtp(String otp, Date todayDate, String id, int otpSentCount);

//    @Query(value = "select is_exist from user_check_access(:roles,:authUser)", nativeQuery = true)
//    boolean checkPermission(String roles, String authUser);

    @Query(value = "select * from  login_user_data(:id)", nativeQuery = true)
    Object getLoginUserData(String id);


//    @Query(value = "select to_jsonb(j) from (select c.id,c.name,c.email,c.mobile,c.whats_app_number as whats_app_number, c.login_id as login_id, c.user_role_id as user_role from app_user c where c.id=:id) j", nativeQuery = true)
//    Optional<Object> getDtoById(String id);

//    @Query(value = "select json_build_object(" + //
//            "'id', cli.id," + //
//            "'name', cli.name," + //
//            "'email',cli.email" +  //
//            ") from app_user cli inner join app_user_role p on p.id=cli.user_role_id where cli.tenant_id=:tenantId and (upper(cli.name \\:\\:text) like upper(concat('%',:search,'%')) or upper(cli.code) like upper(concat('%',:search,'%')) ) ",//
//            nativeQuery = true)
//    Page<String> findAllData(String tenantId, String search, Pageable pageable);

//    @Query(value = "select to_jsonb(j) from (select c.id,concat(c.name->'en', '(',c.code,')') as name,c.code from app_user_role c where c.tenant_id=:tenantId and c.status='ACTIVE' order by c.code) j", nativeQuery = true)
//    List<Object> getDropdownData(String tenantId);

//    @Query(value = "select json_agg(j) from (select p.code,p.name ->'en' as english_name,p.name -> 'mr' as marathi_name,p.status from app_user c inner join app_user_role p on p.id=c.user_role_id where c.tenant_id=:tenantId) j ", nativeQuery = true)
//    Optional<String> getExportData(String tenantId);

//    @Query(value = "select id,code, login_id as loginId, otp,device_id as deviceId,decimal_scale as decimalScale,time_zone as TimeZone,otp_date as otpDate,locked,deleted,status,tenant_type as TenantType, tenant_id as TenantId,lang_code as LangCode,failed_attempts as failedAttempts from app_user where id = :id and deleted = false", nativeQuery = true)
//    Optional<AuthUserProjection> getAuthUserById(String id);

}
