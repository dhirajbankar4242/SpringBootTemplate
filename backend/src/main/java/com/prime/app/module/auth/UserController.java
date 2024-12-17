package com.prime.app.module.auth;

import com.prime.app.config.security.jwt.JwtTokenFactory;
import com.prime.app.module.auth.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final JwtTokenFactory tokenFactory;

//    @GetMapping("{id}")
//    public Object getById(@PathVariable String id) throws NoRecordFoundException {
//        return service.getById(id);
//    }

//    @GetMapping
//    public LoginResponse getCurrantProfile() throws ApplicationException {
//        AuthUserProjection loginUserDetails = service.getAuthUserById(SecurityLibrary.getId()).orElseThrow(() -> new ApplicationException(MessageUtils.get(GlobalSetting.MSG_BAD_CREDENTIALS)));
//        AuthenticatedUser userContext = new AuthenticatedUser(loginUserDetails.getId(), loginUserDetails.getTimeZone(), loginUserDetails.getLangCode(), loginUserDetails.getDecimalScale(), loginUserDetails.getTenantType(), loginUserDetails.getTenantId(), loginUserDetails.getCode());
//        return new LoginResponse(tokenFactory.createAccessJwtToken(userContext), tokenFactory.createRefreshToken(userContext), service.getLoginUserData(loginUserDetails.getId()));
//    }

//    @PostMapping("page")
//    public Page<String> get(@RequestBody PaginationRequest pageable) {
//        return service.findAll(SecurityLibrary.getTenantId(), pageable);
//    }

//    @PostMapping
//    public void save(@RequestBody UserDto dto) throws ApplicationException {
//        service.save(dto, SecurityLibrary.getTenantId());
//    }

//    @PutMapping
//    public void update(@RequestBody UserDto dto) throws NoRecordFoundException, ApplicationException {
//        service.update(dto);
//    }

//    @GetMapping("/export")
//    public ResponseEntity<byte[]> downloadExcel() throws NoRecordFoundException, ApplicationException {
//
//        byte[] excelBytes = ExcelUtils.generateExcelFile(service.getExportData(SecurityLibrary.getTenantId()));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment", "data.xlsx");
//        return new ResponseEntity<>(excelBytes, headers, 200);
//    }
}
