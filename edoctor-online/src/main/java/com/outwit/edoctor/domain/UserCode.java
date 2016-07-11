package com.outwit.edoctor.domain;

import com.outwit.edoctor.infrastructure.Term.StatusCode;

public enum UserCode implements StatusCode {

    REPEAT_SEND(100);

    private int number;

    UserCode(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
