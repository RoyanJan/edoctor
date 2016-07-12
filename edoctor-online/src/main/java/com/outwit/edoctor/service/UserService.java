package com.outwit.edoctor.service;

public interface UserService {

    int VERIFYCODE_EXPIRE_SECONDS = 60;
    void sendVerifyCode(String sessionId);
}
