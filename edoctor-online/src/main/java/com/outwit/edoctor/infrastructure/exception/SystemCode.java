package com.outwit.edoctor.infrastructure.exception;

public enum SystemCode implements ErrorCode {
    ;

    private final int number;

    private SystemCode(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
