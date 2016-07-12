package com.outwit.edoctor.infrastructure.exception;

import com.outwit.edoctor.infrastructure.Term.StatusCode;

public enum SystemCode implements StatusCode {

    SUCCESS(200),
    INTERNAL_ERROR(500),
    REMOTE_PROCESS_ERROR(501);

    private int number;

    SystemCode(int number){
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
