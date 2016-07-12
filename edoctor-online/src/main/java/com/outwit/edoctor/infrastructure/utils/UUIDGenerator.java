package com.outwit.edoctor.infrastructure.utils;

import java.util.UUID;

public class UUIDGenerator {

    public static String generateUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        assert uuid.length() == 32 : "UUID generate failure .";
        return uuid;
    }

}
