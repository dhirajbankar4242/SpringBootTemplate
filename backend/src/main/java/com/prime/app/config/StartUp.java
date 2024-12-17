package com.prime.app.config;

import com.prime.app.module.auth.service.user.UserService;
import com.prime.app.module.common.enums.TenantType;
import com.prime.app.module.company.Company;
import com.prime.app.module.company.CompanyRepository;
import com.prime.app.utils.LanguageUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@RequiredArgsConstructor
@Log4j2
public class StartUp {
    public static final String UTF_8 = "UTF-8";
    private final DataSource dataSource;
    private final UserService userService;
//    private final IdSettingService idSettingService;
//    private final AccessRightsService accessRightsService;
    private final CompanyRepository companyProfileRepository;

    @PostConstruct
    void doInit() {
        log.info("<==============================Application Starting==========================================>");
        try {
            loadStoreProcedureData();
            createDefaultUser();
        } catch (Exception e) {
            log.info("Error While Creating default setting --->" + e.getMessage(), e);
        }
        log.info("<=====>***<============>Application Started<===========>***<=================================>");
    }

    private void loadStoreProcedureData() {
        try {
            log.info("Loading store procedure...");
            ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, UTF_8, //
                    new ClassPathResource("data/stored_procedure.sql")//
            );
            resourceDatabasePopulator.setSeparator("DELIMITER");
            DatabasePopulatorUtils.execute(resourceDatabasePopulator, dataSource);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    private void createDefaultUser() {
        Company companyProfile = companyProfileRepository.findByCode(GlobalSetting.SUPER_COMPANY_CODE);
        if (companyProfile == null) {
            companyProfile = new Company();
            companyProfile.setName("Super Admin Company");
            companyProfile.setCode(GlobalSetting.SUPER_COMPANY_CODE);
            companyProfile = companyProfileRepository.save(companyProfile);
            userService.createDefaultUserData(companyProfile.getId(), "SUPER_ADMIN", "Super1234", "ADMIN", TenantType.SUPER_ADMIN, LanguageUtils.toMultiLang("Super Admin"));
        }
    }

}
