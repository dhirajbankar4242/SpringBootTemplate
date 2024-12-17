package com.prime.app.config.exceptions;

public class SecurityException extends Exception {

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Exception e) {
        super(message, e);
    }
}
