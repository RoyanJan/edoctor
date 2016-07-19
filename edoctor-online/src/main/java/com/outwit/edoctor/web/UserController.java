package com.outwit.edoctor.web;

import com.outwit.edoctor.domain.User;
import com.outwit.edoctor.domain.UserCode;
import com.outwit.edoctor.domain.UserType;
import com.outwit.edoctor.infrastructure.Term.Interaction;
import com.outwit.edoctor.infrastructure.exception.ApplicationException;
import com.outwit.edoctor.infrastructure.utils.PasswordHelper;
import com.outwit.edoctor.infrastructure.utils.UUIDGenerator;
import com.outwit.edoctor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/verifyCode/{telephone}", method = RequestMethod.GET)
    public Interaction fetchVerifyCode(HttpSession session, @PathVariable String telephone) {
        String sessionId = session.getId();
        log.debug("Session id " + sessionId + ",telephone " + telephone + " coming in ...");
        userService.sendVerifyCode(sessionId, telephone);
        return new Interaction(UserCode.SENDVC_SUCCESS, "Verify code already send .");
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public Interaction regist(@ModelAttribute AuthenticationDTO authenticationDTO) {
        log.debug("register vo content : " + authenticationDTO.toString());
        if (!userService.verifyCode(authenticationDTO.getTelephone(), authenticationDTO.getVerifyCode())) {
            throw new ApplicationException(UserCode.VERIFY_FAILURE);
        }
        log.debug("verify code successfully .");
        User user = buildUser(authenticationDTO);
        userService.createUser(user);
        log.debug("create user successfully .");
        return new Interaction(UserCode.REGIST_SUCCESS, "Regist successfully .");
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public Interaction authenticateInfo() {
        return new Interaction(UserCode.LOGIN_FAILURE, "Please login again .");
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Interaction login(@ModelAttribute AuthenticationDTO authenticationDTO) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(authenticationDTO.getTelephone());
        token.setPassword(authenticationDTO.getPlainTextPassword().toCharArray());
        token.setRememberMe(true);
        try {
            subject.login(token);
            // TODO 最近登录
        } catch (AuthenticationException e) {
            throw new ApplicationException("Login failed !", e, UserCode.LOGIN_FAILURE);
        }
        return new Interaction(UserCode.LOGIN_SUCCESS, "Login successfully");
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public Interaction logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Interaction(UserCode.LOGOUT_SUCCESS, "Logout successfully");
    }

    @RequestMapping(path = "/user/password", method = RequestMethod.PUT)
    public Interaction forgetPassword(@ModelAttribute AuthenticationDTO authenticationDTO) {
        if (!userService.verifyCode(authenticationDTO.getTelephone(), authenticationDTO.getVerifyCode())) {
            throw new ApplicationException(UserCode.VERIFY_FAILURE);
        }
        User user = new User();
        user.setTelephone(authenticationDTO.getTelephone());
        String randomSalt = UUIDGenerator.generateUUID();
        user.setSalt(randomSalt);
        user.setPassword(new PasswordHelper().encryptPassword(authenticationDTO.getPlainTextPassword() + randomSalt));
        userService.changePassword(user);
        return new Interaction(UserCode.REGIST_SUCCESS, "Change successfully");
    }

    private User buildUser(AuthenticationDTO authenticationDTO) {
        String randomSalt = UUIDGenerator.generateUUID();
        User user = new User();
        user.setId(UUIDGenerator.generateUUID());
        user.setTelephone(authenticationDTO.getTelephone());
        user.setName(user.getId());
        user.setType(authenticationDTO.getIsUser() ? UserType.NORMAL : UserType.DOCTOR);
        user.setPassword(new PasswordHelper().encryptPassword(authenticationDTO.getPlainTextPassword() + randomSalt));
        user.setSalt(randomSalt);
        user.setCreateDate(LocalDateTime.now());
        user.setLastAccess(LocalDateTime.now());
        return user;
    }

}
