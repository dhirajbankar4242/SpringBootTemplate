package com.prime.app.module.company;

import com.prime.app.config.exceptions.ApplicationException;
import com.prime.app.config.exceptions.NoRecordFoundException;
import com.prime.app.config.security.jwt.SecurityLibrary;
import com.prime.app.module.common.dto.PaginationRequest;
import com.prime.app.module.company.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService service;


    @PostMapping("sign-up")
    public void signUp(@RequestBody SignUpDto dto) {
        service.signUp(dto);
    }

    @PostMapping("resend-otp")
    public void resendOtp(@RequestBody SignUpDto dto) {
        service.signUp(dto);
    }

    @PostMapping("submit-otp")
    public void submitOtp(@RequestBody SignUpDto dto) throws ApplicationException {
        service.submitOtp(dto);
    }

    @GetMapping("{id}")
    public Object getById(@PathVariable String id) throws NoRecordFoundException {
        return service.getById(id);
    }

    @GetMapping
    public Object geMyCompany() throws NoRecordFoundException {
        return service.getById(SecurityLibrary.getTenantId());
    }

    @PostMapping("page")
    public Page<String> get(@RequestBody PaginationRequest pageable) {
        return service.findAll(SecurityLibrary.getTenantId(), pageable);
    }

    @PostMapping("/invite")
    public void save(@RequestBody CompanyDto dto) throws ApplicationException {
        service.save(dto, SecurityLibrary.getTenantId());
    }

    @PutMapping
    public void update(@RequestBody CompanyDto dto) throws NoRecordFoundException, ApplicationException {
        service.update(dto);
    }

    @PutMapping("/update-profile")
    public void updateProfile(@RequestBody CompanyDto dto) throws NoRecordFoundException, ApplicationException {
        service.updateProfile(dto, SecurityLibrary.getTenantId());
    }

//    @GetMapping("/export")
//    public ResponseEntity<byte[]> downloadExcel() throws NoRecordFoundException, ApplicationException {
//
//        byte[] excelBytes = ExcelUtils.generateExcelFile(service.getExportData(SecurityLibrary.getTenantId()));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment", "data.xlsx");
//
//        return new ResponseEntity<>(excelBytes, headers, 200);
//    }


}
