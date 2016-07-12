package com.outwit.edoctor.service;

import com.outwit.edoctor.domain.UserCode;
import com.outwit.edoctor.infrastructure.cache.RedisService;
import com.outwit.edoctor.infrastructure.exception.ApplicationException;
import com.outwit.edoctor.infrastructure.exception.SystemCode;
import com.outwit.edoctor.infrastructure.sms.SMSService;
import com.outwit.edoctor.infrastructure.utils.VerifyCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private SMSService smsService;

    @Override
    public void sendVerifyCode(String sessionId) {
        if (redisService.isCached(sessionId)) {
            log.debug("User session id : " + sessionId + ", has send verify code repeatably .");
            throw new ApplicationException(UserCode.REPEAT_SEND);
        }
        String verifyCode = VerifyCodeGenerator.generatorVerifyCode();
        log.debug("User session id : " + sessionId + ", fetch verify code :" + verifyCode);
        try {
            redisService.cacheIt(sessionId, verifyCode, VERIFYCODE_EXPIRE_SECONDS);
            smsService.sendMessage(verifyCode);
        } catch (Exception e) {
            log.error("redis server or remote sms ");
            throw new ApplicationException(SystemCode.REMOTE_PROCESS_ERROR);
        }
        log.debug("User session id : " + sessionId + "sent verify code successfully");
    }
}
