package com.youtap.enums;

public enum ResponseCode {
    BAD_REQUEST(400),
    CREATED(201),
    CONFLICT(400),
    NOT_FOUND(404),
    INTERNAL_ERROR(500),
    SUCCESS(200);
    private int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}