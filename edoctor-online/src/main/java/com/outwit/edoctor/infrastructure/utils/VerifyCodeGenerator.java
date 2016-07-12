package com.outwit.edoctor.infrastructure.utils;

import java.util.Random;

public class VerifyCodeGenerator {

    public static String generatorVerifyCode() {
        String verifyCode = String.format("%06d", new Random().nextInt(1000000));
        assert verifyCode.length() == 6 : "Verify code generate failure";
        return verifyCode;
    }
}
