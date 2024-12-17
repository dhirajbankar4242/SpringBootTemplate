package com.prime.app.module.common.repository;

import com.prime.app.module.common.entity.IdSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IdSettingRepository extends JpaRepository<IdSetting, String> {

    @Query(value = "select * from  id_setting  where tenant_id = :tenantId and name=:idType", nativeQuery = true)
    IdSetting findByTenantIdAndName(String tenantId, String idType);

}
