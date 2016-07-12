package com.outwit.edoctor.service;

import com.outwit.edoctor.domain.User;

public interface UserService {

    int VERIFYCODE_EXPIRE_SECONDS = 60;

    void sendVerifyCode(String sessionId, String telephone);

    void createUser(User user);

    boolean verifyCode(String telephone, String code);
}
