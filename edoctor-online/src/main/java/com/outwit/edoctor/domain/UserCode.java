package com.outwit.edoctor.domain;

import com.outwit.edoctor.infrastructure.Term.StatusCode;

public enum UserCode implements StatusCode {

    REPEAT_SEND(100),
    REPEAT_USER(101),
    SENDVC_SUCCESS(102),
    REGIST_SUCCESS(103),
    VERIFY_FAILURE(104);

    private int number;

    UserCode(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
