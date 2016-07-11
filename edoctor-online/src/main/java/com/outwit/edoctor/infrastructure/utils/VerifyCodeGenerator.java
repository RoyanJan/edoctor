package com.outwit.edoctor.infrastructure.utils;

import java.util.Random;

public class VerifyCodeGenerator {

    public static String generatorVerifyCode() {
        return String.format("%06d", new Random().nextInt(1000000));
    }
}
