package com.outwit.edoctor.domain;

public enum UserType {
    NORMAL(0),
    DOCTOR(1);

    private int value;

    UserType(int num) {
        this.value = num;
    }

    public int getValue() {
        return value;
    }

    public static UserType valueOf(int value) {
        switch (value) {
            case 0:
                return NORMAL;
            case 1:
                return DOCTOR;
            default:
                return NORMAL;
        }
    }
}
