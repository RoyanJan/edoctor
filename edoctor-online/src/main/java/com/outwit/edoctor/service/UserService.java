package com.outwit.edoctor.service;

import com.outwit.edoctor.domain.User;

import java.util.Set;

public interface UserService {

    int VERIFYCODE_EXPIRE_SECONDS = 60;

    void sendVerifyCode(String sessionId, String telephone);

    void createUser(User user);

    boolean verifyCode(String telephone, String code);

    User findUserByTelephone(String telephone);

    Set<String> findRoles(String telephone);

    void changePassword(User user);
}
