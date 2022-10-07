package com.youtap.exception;

public class YouTapServiceException extends RuntimeException {

    private int responseCode = 500;

    public YouTapServiceException(String message, int responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public YouTapServiceException(String message) {
        super(message);
    }

    public int getResponseCode() {
        return responseCode;
    }

}
