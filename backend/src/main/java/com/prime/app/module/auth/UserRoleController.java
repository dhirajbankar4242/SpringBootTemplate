package com.prime.app.module.auth;

import com.prime.app.module.auth.service.userrole.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user-role")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService service;

//    @GetMapping("{id}")
//    public Object getById(@PathVariable String id) throws NoRecordFoundException {
//        return service.getById(id);
//    }

//    @PostMapping("page")
//    public Page<String> get(@RequestBody PaginationRequest pageable) {
//        return service.findAll(SecurityLibrary.getTenantId(), pageable);
//    }

//    @PostMapping
//    public void save(@RequestBody UserRoleDto dto) throws ApplicationException {
//        service.save(dto, SecurityLibrary.getTenantId());
//    }

//    @PutMapping
//    public void update(@RequestBody UserRoleDto dto) throws NoRecordFoundException, ApplicationException {
//        service.update(dto);
//    }

//    @GetMapping("/export")
//    public ResponseEntity<byte[]> downloadExcel() throws NoRecordFoundException, ApplicationException {
//
//        byte[] excelBytes = ExcelUtils.generateExcelFile(service.getExportData(SecurityLibrary.getTenantId()));
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment", "data.xlsx");
//
//        return new ResponseEntity<>(excelBytes, headers, 200);
//    }

//    @GetMapping("dropdown")
//    public List<Object> getDropdownData(@RequestParam String search) {
//        return service.getDropdownData(search, SecurityLibrary.getTenantId());
//    }
}
