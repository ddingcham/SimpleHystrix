package com.ddingcham.example.hystrix.domain.support;

public enum RestAreaGrade {
    최우수(1), 우수(2), 일반(3);
    private final int value;

    private RestAreaGrade(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
