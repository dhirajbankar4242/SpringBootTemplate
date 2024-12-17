package com.prime.app.module.auth.service.security_token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SecurityTokenServiceImpl implements SecurityTokenService {

//    private final SecurityTokenRepository securityTokenRepository;

//    @Override
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
//    public SecurityToken generateTokenWithValidityForUser(AuthUserProjection tokenUser) {
//        return securityTokenRepository.save(new SecurityToken(tokenUser));
//    }

//    @Override
//    public boolean validateToken(SecurityToken securityToken) throws ApplicationException, NoRecordFoundException {
//        return validateUserToken(securityToken.getUserId(), securityToken.getToken());
//    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(SecurityToken securityToken) throws NoRecordFoundException {
//        SecurityToken token = securityTokenRepository.findById(securityToken.getToken()).orElseThrow(NoRecordFoundException::new);
//        token.setUsedDate(new Date());
//        token.setDeleted(Boolean.TRUE);
//        securityTokenRepository.save(token);
//    }

//    @Override
//    public boolean validateUserToken(String userId, String securityToken) throws ApplicationException, NoRecordFoundException {
//        SecurityToken token = securityTokenRepository.findById(securityToken).orElseThrow(NoRecordFoundException::new);
//        validate(userId, token);
//        return true;
//    }

//    private void validate(String userId, SecurityToken token) throws ApplicationException {
//        if (token == null) {
//            throw new ApplicationException("Invalid Security Token.");
//        }
//        if (token.getUsedDate() != null) {
//            throw new ApplicationException("Invalid Security Token. Token is already used once.");
//        }
//        if (userId != null && (token.getUserId() == null || !token.getUserId().equals(userId))) {
//            throw new ApplicationException("Invalid Security Token. Token not valid for user.");
//        }
//
//        if (Boolean.TRUE == token.getDeleted()) {
//            throw new ApplicationException("Invalid Security Token. Token not valid anymore.");
//        }
//
//        if (token.getExpiryDate() != null) {
//            Date now = new Date();
//            if (now.after(token.getExpiryDate())) {
//                throw new ApplicationException("Invalid Security Token." + token.getExpiryDate());
//            }
//        }
//    }

}
