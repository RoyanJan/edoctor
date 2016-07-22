package com.outwit.edoctor.service;

import com.outwit.edoctor.domain.User;
import com.outwit.edoctor.domain.UserCode;
import com.outwit.edoctor.infrastructure.cache.RedisService;
import com.outwit.edoctor.infrastructure.exception.ApplicationException;
import com.outwit.edoctor.infrastructure.exception.SystemCode;
import com.outwit.edoctor.infrastructure.mapper.UserMapper;
import com.outwit.edoctor.infrastructure.sms.SMSService;
import com.outwit.edoctor.infrastructure.utils.VerifyCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private SMSService smsService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void sendVerifyCode(String sessionId, String telephone) {
        if (redisService.isCached(sessionId) || redisService.isCached(telephone)) {
            log.info("User session id : " + sessionId + " telephone : " + telephone + ", has send verify code repeatably .");
            throw new ApplicationException(UserCode.REPEAT_SEND);
        }
        String verifyCode = VerifyCodeGenerator.generatorVerifyCode();
        log.info("User session id : " + sessionId + " telephone : " + telephone + ", fetch verify code :" + verifyCode);
        try {
            redisService.cacheIt(telephone, verifyCode, VERIFYCODE_EXPIRE_SECONDS);
            redisService.cacheIt(sessionId, "active", VERIFYCODE_EXPIRE_SECONDS);
            smsService.sendMessage(telephone, verifyCode);
        } catch (Exception e) {
            log.error("redis server or remote sms ");
            throw new ApplicationException(SystemCode.REMOTE_PROCESS_ERROR);
        }
        log.info("User session id : " + sessionId + " telephone : " + telephone + " sent verify code successfully");
    }

    @Override
    public boolean verifyCode(String telephone, String code) {
        return redisService.getValue(telephone).equals(code);
    }

    @Override
    public void createUser(User user) {

        if (isUserExist(user)) {
            log.error("FixLength " + user.getTelephone() + " has already registed !");
            throw new ApplicationException(UserCode.REPEAT_USER);
        }
        userMapper.createUser(user);
        log.info("FixLength " + user.getTelephone() + " regist successfully .");
    }

    @Override
    public User findUserByTelephone(String telephone) {
        return userMapper.fetchUser(telephone);
    }

    @Override
    public Set<String> findRoles(String telephone) {
        return userMapper.fetchRoles(telephone)
                .stream().map(Enum::toString).collect(Collectors.toSet());
    }

    @Override
    public void changePassword(User user) {
        if (!isUserExist(user)) {
            log.error("FixLength " + user.getTelephone() + " is not exist !");
            throw new ApplicationException(UserCode.NOT_EXIST);
        }
        userMapper.updateUserPassword(user);
        log.info("FixLength " + user.getTelephone() + " change password successfully .");
    }

    private boolean isUserExist(User user) {
        return userMapper.hasUser(user.getTelephone()) > 0;
    }
}
