package com.prime.app.module;

//import com.prime.app.config.exceptions.ApplicationException;
//import com.prime.app.config.security.jwt.SecurityLibrary;
//import com.prime.app.module.common.enums.TenantType;
//import com.prime.app.module.milk_collection.collection.MilkCollectionService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("dashboard/{tenantType}")
public class DashboardController {

//    private final MilkCollectionService milkCollectionService;
//
//    @GetMapping
//    public Object dashboard(@RequestParam String date, @PathVariable TenantType tenantType) throws ApplicationException {
//        return milkCollectionService.getDashboardData(date, SecurityLibrary.getTenantId(), tenantType, SecurityLibrary.getId());
//    }
}
