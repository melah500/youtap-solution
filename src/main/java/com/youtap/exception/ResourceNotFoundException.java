package com.youtap.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int responseCode = 404;

    public ResourceNotFoundException(String message, int responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public int getResponseCode() {
        return responseCode;
    }

}
