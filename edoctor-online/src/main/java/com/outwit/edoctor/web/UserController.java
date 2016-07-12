package com.outwit.edoctor.web;

import com.outwit.edoctor.domain.User;
import com.outwit.edoctor.domain.UserCode;
import com.outwit.edoctor.domain.UserType;
import com.outwit.edoctor.infrastructure.Term.Interaction;
import com.outwit.edoctor.infrastructure.exception.ApplicationException;
import com.outwit.edoctor.infrastructure.utils.UUIDGenerator;
import com.outwit.edoctor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

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
    public Interaction regist(@ModelAttribute RegisterDTO registerDTO) {
        log.debug("register vo content : " + registerDTO.toString());
        if (!userService.verifyCode(registerDTO.getTelephone(), registerDTO.getVerifyCode())) {
            throw new ApplicationException(UserCode.VERIFY_FAILURE);
        }
        log.debug("verify code successfully .");
        User user = buildUser(registerDTO);
        userService.createUser(user);
        log.debug("create user successfully .");
        return new Interaction(UserCode.REGIST_SUCCESS, "Regist successfully .");
    }

    private User buildUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setId(UUIDGenerator.generateUUID());
        user.setTelephone(registerDTO.getTelephone());
        user.setName(user.getId());
        user.setType(registerDTO.getIsUser() ? UserType.NORMAL : UserType.DOCTOR);
        // TODO password & salt
        user.setCreateDate(new Date());
        user.setLastAccess(new Date());
        return user;
    }

}
