package com.prime.app.module.auth.repository;

import com.prime.app.module.auth.entity.AccessRights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessRightsRepository extends JpaRepository<AccessRights, String> {

//    @Query(value = "select * from app_access_rights where owner = :owner and parent_acl_value is null order by acl_order", nativeQuery = true)
//    List<AccessRights> findByOwnerAndParentIsNullOrderByAclOrder(@Param("owner") boolean owner);

//    @Query(value = "select distinct acl from AccessRights acl left outer join acl.parent pr left outer join fetch acl.children ch where acl.client = :client and pr is null order by acl.aclOrder")
//    List<AccessRights> findByClientAndParentIsNullOrderByAclOrder(@Param("client") boolean client);


//    @Query(value = "select * from app_access_rights where acl_value = :aclValue", nativeQuery = true)
//    Optional<AccessRights> findByAclValue(@Param("aclValue") String aclValue);

//    @Query(value = "select * from app_access_rights where parent_acl_value = :parent order by acl_order", nativeQuery = true)
//    List<AccessRights> findByParentIdOrderByAclOrder(@Param("parent") String id);

    @Query(value = "select * from app_access_rights where owner = :owner  order by acl_order", nativeQuery = true)
    List<AccessRights> findByOwner(@Param("owner") boolean b);

//    @Query(value = "select * from app_access_rights where client = :client  order by acl_order", nativeQuery = true)
//    List<AccessRights> findByClient(@Param("client") boolean b);


//    @Query(value = "select distinct acl from AccessRights acl left outer join fetch acl.children ch where acl.parent.aclValue=:aclValue")
//    List<AccessRights> findByParentId(@Param("aclValue") String aclValue);

}
