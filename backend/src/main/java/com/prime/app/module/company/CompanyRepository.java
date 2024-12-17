package com.prime.app.module.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, String> {

    @Query(value = "select json_build_object(" + //
            "'id', cli.id," + //
            "'name', cli.name," + //
            "'status',cli.status," +  //
            "'code',cli.code," +  //
            ") from company cli where cli.tenant_id=:tenantId and (upper(cli.name \\:\\:text) like upper(concat('%',:search,'%')) or upper(cli.code) like upper(concat('%',:search,'%')) ) ",//
            nativeQuery = true)
    Page<String> findAllData(String tenantId, String search, Pageable pageable);

    @Query(value = "select to_jsonb(j) from (select c.id,c.name,c.mobile,c.code,c.address,c.gst_number as gstNumber,c.registration_number as registrationNumber,c.person_name as personName,c.person_mobile as personMobile,c.email from company c where c.id=:id) j", nativeQuery = true)
    Optional<Object> getDtoById(String id);

    @Query(value = "select json_agg(j) from (select c.code,c.name ->'en' as english_name,c.name -> 'mr' as marathi_name,c.status from company c where c.tenant_id=:tenantId) j ", nativeQuery = true)
    Optional<String> getExportData(String tenantId);


    @Query("select id from Company where code=:code")
    String findIdByCode(String code);

    @Query("select c from Company c where c.mobile=:mobile")
    Company findByMobile(String mobile);

    @Query("select c from Company c where c.code=:code")
    Company findByCode(String code);
}
