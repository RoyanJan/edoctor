package com.outwit.edoctor.infrastructure.utils;

import java.security.SecureRandom;

public class VerifyCodeGenerator {

    public static String generatorVerifyCode() {
        String verifyCode = String.format("%06d", new SecureRandom().nextInt(1000000));
        assert verifyCode.length() == 6 : "Verify code generate failure";
        return verifyCode;
    }
}
