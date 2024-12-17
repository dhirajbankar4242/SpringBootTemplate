package com.prime.app.module.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prime.app.config.GlobalSetting;
import com.prime.app.config.exceptions.ApplicationException;
import com.prime.app.config.security.jwt.AuthenticatedUser;
import com.prime.app.config.security.jwt.JwtTokenFactory;
import com.prime.app.config.security.jwt.LoginRequest;
import com.prime.app.module.auth.dto.AuthUserProjection;
import com.prime.app.module.auth.dto.LoginResponse;
import com.prime.app.module.auth.service.user.UserService;
import com.prime.app.module.common.enums.Status;
import com.prime.app.utils.MessageUtils;
import com.prime.app.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
//@Tag(name = "Auth", description = "Auth API")
public class AuthController {

//    private final NotificationService communicationUtils;
    private final JwtTokenFactory tokenFactory;
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

//    @GetMapping
//    public void getOtpToLogin(@RequestParam String mobile, HttpServletRequest httpServletRequest) throws ApplicationException {
//        OtpUserProjection loginUserDetails = userService.getOtpUserByLoginId(mobile).orElseThrow(() -> new ApplicationException(MessageUtils.get(GlobalSetting.MSG_BAD_CREDENTIALS)));
//
//        if (Boolean.TRUE.equals(loginUserDetails.getLocked())) {
//            throw new ApplicationException(MessageUtils.get(GlobalSetting.MSG_ACCOUNT_LOCK));
//        }
//
//        if (Status.INACTIVE == loginUserDetails.getStatus()) {
//            throw new ApplicationException(MessageUtils.get(GlobalSetting.MSG_ACCOUNT_INACTIVE));
//        }
//        if (loginUserDetails.getOtpSentCount() > 6) {
//            throw new ApplicationException("Otp sent limit exceed!");
//        }
//
//        String OTP = StringUtils.generateOtp(6);
//        String ip = getClientIp(httpServletRequest);
//        log.info("OTP requested for {} using ip {}", mobile, ip);
//        communicationUtils.sendSMS(new SMSDto(mobile, loginUserDetails.getTenantId(), SMSDto.OTP_TEMPLATE, OTP));
//        userService.updateOtp(encoder.encode(OTP), loginUserDetails.getId(), loginUserDetails.getOtpSentCount() + 1);
//
//    }
//
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest) throws ApplicationException, JsonProcessingException {
        if (StringUtils.isEmpty(request.getUsername()) || StringUtils.isEmpty(request.getPassword())) {
            throw new ApplicationException(MessageUtils.get(GlobalSetting.MSG_BAD_CREDENTIALS));
        }
        AuthUserProjection loginUserDetails = userService.getAuthUserByLoginId(request.getUsername()).orElseThrow(() -> new ApplicationException(MessageUtils.get(GlobalSetting.MSG_BAD_CREDENTIALS)));
        if (!encoder.matches(request.getPassword(), loginUserDetails.getPassword())) {
            updateFailedAttemnt(loginUserDetails);
            throw new ApplicationException(MessageUtils.get(GlobalSetting.BAD_CREDENTIALS));
        }
        if (Boolean.TRUE.equals(loginUserDetails.getLocked())) {
            throw new ApplicationException(MessageUtils.get(GlobalSetting.MSG_ACCOUNT_LOCK));
        }
        if (Status.INACTIVE == loginUserDetails.getStatus()) {
            throw new ApplicationException(MessageUtils.get(GlobalSetting.MSG_ACCOUNT_INACTIVE));
        }
        AuthenticatedUser userContext = new AuthenticatedUser(loginUserDetails.getId(), loginUserDetails.getTimeZone(), loginUserDetails.getLangCode(), loginUserDetails.getDecimalScale(), loginUserDetails.getTenantType(), loginUserDetails.getTenantId(), loginUserDetails.getCode());
        updateLoginData(request, httpServletRequest, loginUserDetails);
        return new LoginResponse(tokenFactory.createAccessJwtToken(userContext), tokenFactory.createRefreshToken(userContext), userService.getLoginUserData(loginUserDetails.getId()));
    }
    private void updateFailedAttemnt(AuthUserProjection loginUserDetails) {
        int failed = loginUserDetails.getFailedAttempts() == null ? 0 : loginUserDetails.getFailedAttempts() + 1;
        if (failed > 6) {
            userService.lockUser(loginUserDetails.getId());
        }
        userService.updateFailedAttemptes(loginUserDetails.getId(), failed);
    }
    private void updateLoginData(LoginRequest request, HttpServletRequest httpServletRequest, AuthUserProjection loginUserDetails) {
        userService.updateUserLastLoginDetails(new Date(), getClientIp(httpServletRequest), loginUserDetails.getId());
    }

    //@Operation(description = "Refresh token")
    @GetMapping(value = "/refresh")
    public String refreshToken(@RequestHeader("token") String tokenPayload) throws ApplicationException, JsonProcessingException {
        String subject = tokenFactory.verifyRefreshToken(tokenPayload);
        AuthUserProjection loginUserDetails = userService.getAuthUserByLoginId(subject).orElseThrow(() -> new ApplicationException(MessageUtils.get(GlobalSetting.MSG_BAD_CREDENTIALS)));
        AuthenticatedUser userContext = new AuthenticatedUser(loginUserDetails.getId(), loginUserDetails.getTimeZone(), loginUserDetails.getLangCode(), loginUserDetails.getDecimalScale(), loginUserDetails.getTenantType(), loginUserDetails.getTenantId(), loginUserDetails.getCode());
        return tokenFactory.createAccessJwtToken(userContext);
    }
//
//    @GetMapping(value = "/permissions")
//    public List<String> getPermissions() {
//        return userService.getAcls(SecurityLibrary.getId());
//    }
//
//    @GetMapping(value = "/timeZone/{timeZone}")
//    public void timeZone(@PathVariable("timeZone") String timeZone) {
//        userService.updateTimeZone(timeZone, SecurityLibrary.getId());
//    }
//
//    @GetMapping(value = "/language/{lanCode}")
//    public void lanCode(@PathVariable("lanCode") String lanCode) {
//        userService.updateLangCode(lanCode, SecurityLibrary.getId());
//    }
//
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        ip = getCorrectIp(ip, request.getHeader("Proxy-Client-IP"));
        ip = getCorrectIp(ip, request.getHeader("WL-Proxy-Client-IP"));
        ip = getCorrectIp(ip, request.getHeader("HTTP_X_FORWARDED_FOR"));
        ip = getCorrectIp(ip, request.getHeader("HTTP_X_FORWARDED"));
        ip = getCorrectIp(ip, request.getHeader("HTTP_X_CLUSTER_CLIENT_IP"));
        ip = getCorrectIp(ip, request.getHeader("HTTP_CLIENT_IP"));
        ip = getCorrectIp(ip, request.getHeader("HTTP_FORWARDED_FOR"));
        ip = getCorrectIp(ip, request.getHeader("HTTP_FORWARDED"));
        ip = getCorrectIp(ip, request.getHeader("HTTP_VIA"));
        ip = getCorrectIp(ip, request.getHeader("REMOTE_ADDR"));
        ip = getCorrectIp(ip, request.getRemoteAddr());
        return ip;
    }

    private static String getCorrectIp(String ip, String request) {
        if (ip == null || ip.isEmpty() || ip.equalsIgnoreCase(GlobalSetting.UNKNOWN)) {
            ip = request;
        }
        return ip;
    }

}
