package com.outwit.edoctor.infrastructure.utils;

import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;

public class PasswordHelper implements PasswordService {

    @Override
    public String encryptPassword(Object o) throws IllegalArgumentException {
        HashRequest request = new HashRequest.Builder()
                .setSource(o)
                .setAlgorithmName("SHA-512")
                .setIterations(2)
                .build();
        return new DefaultHashService().computeHash(request).toHex();
    }

    @Override
    public boolean passwordsMatch(Object o, String s) {
        return s.equals((String) o);
    }
}
