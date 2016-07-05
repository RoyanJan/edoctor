package com.outwit.edoctor.infrastructure.exception;

import com.outwit.edoctor.infrastructure.Term.StatusCode;

public enum SystemCode implements StatusCode {

    INTERNAL_ERROR(500);

    private final int number;

    private SystemCode(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
