package com.prime.app.module.auth.repository;

import com.prime.app.module.auth.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    @Query(value = "select * from app_user_role where tenant_id = :tenantId and code = :name", nativeQuery = true)
    UserRole findByCodeAndTenantId(@Param("name") String name, String tenantId);

//    boolean existsByNameAndTenantId(String name, String tenantId);

//    @Query(value = "select distinct acl from UserRole u inner join  u.accessControlList  acl left outer join fetch acl.children acc where u.id = :id and acc.parent is null order by acl.aclOrder")
//    List<AccessRights> getAcls(String id);

//    @Query(value = "select to_jsonb(j) from (select c.id,c.name,c.status from app_user_role c where c.id=:id) j", nativeQuery = true)
//    Optional<Object> getDtoById(String id);

//    @Query(value = "select json_build_object(" + //
//            "'id', cli.id," + //
//            "'name', cli.name," + //
//            "'status',cli.status," +  //
//            "'code',cli.code," +  //
//            "'Created By',cb.name," + //
//            "'Created Date',cli.created_date" + //
//            ") from app_user_role cli inner join app_user cb on cb.id=cli.created_by_id where cli.tenant_id=:tenantId and (upper(cli.name \\:\\:text) like upper(concat('%',:search,'%')) or upper(cli.code) like upper(concat('%',:search,'%')) ) ",//
//            nativeQuery = true)
//    Page<String> findAllData(String tenantId, String search, Pageable pageable);

//    @Query(value = "select json_agg(j) from (select c.code,c.name ->'en' as english_name,c.name -> 'mr' as marathi_name,c.status from app_user_role c where c.tenant_id=:tenantId) j ", nativeQuery = true)
//    Optional<String> getExportData(String tenantId);

//    @Query(value = "select to_jsonb(j) from (select c.id,concat(c.name->'en', '(',c.code,')') as name,c.code from app_user_role c where c.tenant_id=:tenantId and c.status='ACTIVE' order by c.code) j", nativeQuery = true)
//    List<Object> getDropdownData(String tenantId);
}
